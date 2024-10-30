$(document).ready(function () {
    let currentWeather;
    let forecast = [];
    let locationError = '';

    function getLocation() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(
                function (position) {
                    const lat = position.coords.latitude;
                    const lon = position.coords.longitude;
                    getWeather(lat, lon);
                },
                function (error) {
                    locationError = 'Unable to retrieve your location';
                    console.error('Geolocation error:', error);
                    displayLocationError(locationError);
                }
            );
        } else {
            locationError = 'Geolocation is not supported by this browser.';
            displayLocationError(locationError);
        }
    }

    function getWeather(lat, lon) {
        const apiUrl = `https://api.open-meteo.com/v1/forecast?latitude=${lat}&longitude=${lon}&current_weather=true&daily=temperature_2m_max,temperature_2m_min,precipitation_sum,weathercode&timezone=auto`;
        
        $.ajax({
            url: apiUrl,
            method: 'GET',
            success: function (data) {
                currentWeather = data.current_weather;
                forecast = data.daily.time.map((time, index) => ({
                    time,
                    temperature_2m_max: data.daily.temperature_2m_max[index],
                    temperature_2m_min: data.daily.temperature_2m_min[index],
                    precipitation_sum: data.daily.precipitation_sum[index],
                    weathercode: data.daily.weathercode[index],
                })).slice(1, 4);
                displayWeather();
            },
            error: function (error) {
                console.error('Error fetching weather data:', error);
            }
        });
    }

    function displayWeather() {
        const currentWeatherDiv = $('#current-weather');
        currentWeatherDiv.html(`
            <img src="${getWeatherIcon(currentWeather.weathercode, isDaytime())}"></img>
            <p>Temperature: ${currentWeather.temperature}°C</p>
            <p>Condition: ${getWeatherDescription(currentWeather.weathercode, isDaytime())}</p>
        `);

        const forecastDiv = $('#forecast');
        forecastDiv.html(forecast.map(day => `
            <div>
                <h3>Date: ${day.time}</h3>
                <img src="${getWeatherIcon(day.weathercode, isDaytime())}"></img>
                <p>Max Temp: ${day.temperature_2m_max}°C</p>
                <p>Min Temp: ${day.temperature_2m_min}°C</p>
                <p>Condition: ${getWeatherDescription(day.weathercode, isDaytime())}</p>
            </div>
        `).join(''));
    }

    function getWeatherIcon(weatherCode, isDaytime) {
        if (!weatherIcons || !weatherIcons[weatherCode]) {
            console.warn(`Weather icon for code ${weatherCode} not found`);
        }
        const timeOfDay = isDaytime ? 'day' : 'night';
        const icon = weatherIcons[weatherCode][timeOfDay]?.image;
        if (!icon) {
            console.warn(`Weather icon for code ${weatherCode} and time of day ${timeOfDay} not found`);
            return 'assets/icons/default.png';
        }
        return icon;
    }

    function getWeatherDescription(weatherCode, isDaytime) {
        if (!weatherIcons || !weatherIcons[weatherCode]) {
            console.warn(`Weather description for code ${weatherCode} not found`);
        }
        const timeOfDay = isDaytime ? 'day' : 'night';
        const description = weatherIcons[weatherCode][timeOfDay]?.description;
        if (!description) {
            console.warn(`Weather description for code ${weatherCode} and time of day ${timeOfDay} not found`);
            return 'Unknown';
        }
        return description;
    }

    function isDaytime() {
        const hour = new Date().getHours();
        return hour >= 6 && hour < 18;
    }

    function displayLocationError(error) {
        $('#location-error').text(error);
    }

    getLocation();
});


const weatherIcons = {
	"0":{
		"day":{
			"description":"Sunny",
			"image":"https://openweathermap.org/img/wn/01d@2x.png"
		},
		"night":{
			"description":"Clear",
			"image":"https://openweathermap.org/img/wn/01n@2x.png"
		}
	},
	"1":{
		"day":{
			"description":"Mainly Sunny",
			"image":"https://openweathermap.org/img/wn/01d@2x.png"
		},
		"night":{
			"description":"Mainly Clear",
			"image":"https://openweathermap.org/img/wn/01n@2x.png"
		}
	},
	"2":{
		"day":{
			"description":"Partly Cloudy",
			"image":"https://openweathermap.org/img/wn/02d@2x.png"
		},
		"night":{
			"description":"Partly Cloudy",
			"image":"https://openweathermap.org/img/wn/02n@2x.png"
		}
	},
	"3":{
		"day":{
			"description":"Cloudy",
			"image":"https://openweathermap.org/img/wn/03d@2x.png"
		},
		"night":{
			"description":"Cloudy",
			"image":"https://openweathermap.org/img/wn/03n@2x.png"
		}
	},
	"45":{
		"day":{
			"description":"Foggy",
			"image":"https://openweathermap.org/img/wn/50d@2x.png"
		},
		"night":{
			"description":"Foggy",
			"image":"https://openweathermap.org/img/wn/50n@2x.png"
		}
	},
	"48":{
		"day":{
			"description":"Rime Fog",
			"image":"https://openweathermap.org/img/wn/50d@2x.png"
		},
		"night":{
			"description":"Rime Fog",
			"image":"https://openweathermap.org/img/wn/50n@2x.png"
		}
	},
	"51":{
		"day":{
			"description":"Light Drizzle",
			"image":"https://openweathermap.org/img/wn/09d@2x.png"
		},
		"night":{
			"description":"Light Drizzle",
			"image":"https://openweathermap.org/img/wn/09n@2x.png"
		}
	},
	"53":{
		"day":{
			"description":"Drizzle",
			"image":"https://openweathermap.org/img/wn/09d@2x.png"
		},
		"night":{
			"description":"Drizzle",
			"image":"https://openweathermap.org/img/wn/09n@2x.png"
		}
	},
	"55":{
		"day":{
			"description":"Heavy Drizzle",
			"image":"https://openweathermap.org/img/wn/09d@2x.png"
		},
		"night":{
			"description":"Heavy Drizzle",
			"image":"https://openweathermap.org/img/wn/09n@2x.png"
		}
	},
	"56":{
		"day":{
			"description":"Light Freezing Drizzle",
			"image":"https://openweathermap.org/img/wn/09d@2x.png"
		},
		"night":{
			"description":"Light Freezing Drizzle",
			"image":"https://openweathermap.org/img/wn/09n@2x.png"
		}
	},
	"57":{
		"day":{
			"description":"Freezing Drizzle",
			"image":"https://openweathermap.org/img/wn/09d@2x.png"
		},
		"night":{
			"description":"Freezing Drizzle",
			"image":"https://openweathermap.org/img/wn/09n@2x.png"
		}
	},
	"61":{
		"day":{
			"description":"Light Rain",
			"image":"https://openweathermap.org/img/wn/10d@2x.png"
		},
		"night":{
			"description":"Light Rain",
			"image":"https://openweathermap.org/img/wn/10n@2x.png"
		}
	},
	"63":{
		"day":{
			"description":"Rain",
			"image":"https://openweathermap.org/img/wn/10d@2x.png"
		},
		"night":{
			"description":"Rain",
			"image":"https://openweathermap.org/img/wn/10n@2x.png"
		}
	},
	"65":{
		"day":{
			"description":"Heavy Rain",
			"image":"https://openweathermap.org/img/wn/10d@2x.png"
		},
		"night":{
			"description":"Heavy Rain",
			"image":"https://openweathermap.org/img/wn/10n@2x.png"
		}
	},
	"66":{
		"day":{
			"description":"Light Freezing Rain",
			"image":"https://openweathermap.org/img/wn/10d@2x.png"
		},
		"night":{
			"description":"Light Freezing Rain",
			"image":"https://openweathermap.org/img/wn/10n@2x.png"
		}
	},
	"67":{
		"day":{
			"description":"Freezing Rain",
			"image":"https://openweathermap.org/img/wn/10d@2x.png"
		},
		"night":{
			"description":"Freezing Rain",
			"image":"https://openweathermap.org/img/wn/10n@2x.png"
		}
	},
	"71":{
		"day":{
			"description":"Light Snow",
			"image":"https://openweathermap.org/img/wn/13d@2x.png"
		},
		"night":{
			"description":"Light Snow",
			"image":"https://openweathermap.org/img/wn/13n@2x.png"
		}
	},
	"73":{
		"day":{
			"description":"Snow",
			"image":"https://openweathermap.org/img/wn/13d@2x.png"
		},
		"night":{
			"description":"Snow",
			"image":"https://openweathermap.org/img/wn/13n@2x.png"
		}
	},
	"75":{
		"day":{
			"description":"Heavy Snow",
			"image":"https://openweathermap.org/img/wn/13d@2x.png"
		},
		"night":{
			"description":"Heavy Snow",
			"image":"https://openweathermap.org/img/wn/13n@2x.png"
		}
	},
	"77":{
		"day":{
			"description":"Snow Grains",
			"image":"https://openweathermap.org/img/wn/13d@2x.png"
		},
		"night":{
			"description":"Snow Grains",
			"image":"https://openweathermap.org/img/wn/13n@2x.png"
		}
	},
	"80":{
		"day":{
			"description":"Light Showers",
			"image":"https://openweathermap.org/img/wn/09d@2x.png"
		},
		"night":{
			"description":"Light Showers",
			"image":"https://openweathermap.org/img/wn/09n@2x.png"
		}
	},
	"81":{
		"day":{
			"description":"Showers",
			"image":"https://openweathermap.org/img/wn/09d@2x.png"
		},
		"night":{
			"description":"Showers",
			"image":"https://openweathermap.org/img/wn/09n@2x.png"
		}
	},
	"82":{
		"day":{
			"description":"Heavy Showers",
			"image":"https://openweathermap.org/img/wn/09d@2x.png"
		},
		"night":{
			"description":"Heavy Showers",
			"image":"https://openweathermap.org/img/wn/09n@2x.png"
		}
	},
	"85":{
		"day":{
			"description":"Light Snow Showers",
			"image":"https://openweathermap.org/img/wn/13d@2x.png"
		},
		"night":{
			"description":"Light Snow Showers",
			"image":"https://openweathermap.org/img/wn/13n@2x.png"
		}
	},
	"86":{
		"day":{
			"description":"Snow Showers",
			"image":"https://openweathermap.org/img/wn/13d@2x.png"
		},
		"night":{
			"description":"Snow Showers",
			"image":"https://openweathermap.org/img/wn/13n@2x.png"
		}
	},
	"95":{
		"day":{
			"description":"Thunderstorm",
			"image":"https://openweathermap.org/img/wn/11d@2x.png"
		},
		"night":{
			"description":"Thunderstorm",
			"image":"https://openweathermap.org/img/wn/11n@2x.png"
		}
	},
	"96":{
		"day":{
			"description":"Light Thunderstorms With Hail",
			"image":"https://openweathermap.org/img/wn/11d@2x.png"
		},
		"night":{
			"description":"Light Thunderstorms With Hail",
			"image":"https://openweathermap.org/img/wn/11n@2x.png"
		}
	},
	"99":{
		"day":{
			"description":"Thunderstorm With Hail",
			"image":"https://openweathermap.org/img/wn/11d@2x.png"
		},
		"night":{
			"description":"Thunderstorm With Hail",
			"image":"https://openweathermap.org/img/wn/11n@2x.png"
		}
	}
};
