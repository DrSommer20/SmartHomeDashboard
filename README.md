<h1 class="code-line" data-line-start=0 data-line-end=1 ><a id="Smart_Home_Dashboard_0"></a>Smart Home Dashboard</h1>
<h2 class="code-line" data-line-start=2 data-line-end=3 ><a id="Overview_2"></a>Overview</h2>
<p class="has-line-data" data-line-start="4" data-line-end="5">The <strong>Smart Home Dashboard</strong> is a RESTful API system designed for controlling, managing, and monitoring smart home devices, users, rooms, and routines. It provides a wide range of functionalities such as device control, user authentication, room management, and automation through routines.</p>
<h1 class="code-line" data-line-start=6 data-line-end=7 ><a id="API_Endpoints_6"></a>API Endpoints</h1>


> Scroll down for code samples, example requests and responses. Select a language for code samples from the tabs above or the mobile navigation menu.

Base URLs:

* <a href="http://localhost:8080">http://localhost:8080</a>

<h1 id="openapi-definition-user-controller">user-controller</h1>

## getUser

<a id="opIdgetUser"></a>

> Code samples

```shell
# You can also use wget
curl -X GET http://localhost:8080/api/user \
  -H 'Accept: */*' \
  -H 'Authorization: string'

```

```http
GET http://localhost:8080/api/user HTTP/1.1
Host: localhost:8080
Accept: */*
Authorization: string

```

```javascript

const headers = {
  'Accept':'*/*',
  'Authorization':'string'
};

fetch('http://localhost:8080/api/user',
{
  method: 'GET',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*',
  'Authorization' => 'string'
}

result = RestClient.get 'http://localhost:8080/api/user',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*',
  'Authorization': 'string'
}

r = requests.get('http://localhost:8080/api/user', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
    'Authorization' => 'string',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('GET','http://localhost:8080/api/user', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/user");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("GET");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
        "Authorization": []string{"string"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("GET", "http://localhost:8080/api/user", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`GET /api/user`

<h3 id="getuser-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|Authorization|header|string|true|none|

> Example responses

> 200 Response

<h3 id="getuser-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="getuser-responseschema">Response Schema</h3>

<aside class="success">
This operation does not require authentication
</aside>

## changeUser

<a id="opIdchangeUser"></a>

> Code samples

```shell
# You can also use wget
curl -X PUT http://localhost:8080/api/user \
  -H 'Content-Type: application/json' \
  -H 'Accept: */*' \
  -H 'Authorization: string'

```

```http
PUT http://localhost:8080/api/user HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: */*
Authorization: string

```

```javascript
const inputBody = '{
  "new-value": "string",
  "field": "string"
}';
const headers = {
  'Content-Type':'application/json',
  'Accept':'*/*',
  'Authorization':'string'
};

fetch('http://localhost:8080/api/user',
{
  method: 'PUT',
  body: inputBody,
  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Content-Type' => 'application/json',
  'Accept' => '*/*',
  'Authorization' => 'string'
}

result = RestClient.put 'http://localhost:8080/api/user',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Content-Type': 'application/json',
  'Accept': '*/*',
  'Authorization': 'string'
}

r = requests.put('http://localhost:8080/api/user', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Content-Type' => 'application/json',
    'Accept' => '*/*',
    'Authorization' => 'string',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('PUT','http://localhost:8080/api/user', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/user");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("PUT");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Content-Type": []string{"application/json"},
        "Accept": []string{"*/*"},
        "Authorization": []string{"string"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("PUT", "http://localhost:8080/api/user", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`PUT /api/user`

> Body parameter

```json
{
  "new-value": "string",
  "field": "string"
}
```

<h3 id="changeuser-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|Authorization|header|string|true|none|
|body|body|[ChangeRequest](#schemachangerequest)|true|none|

> Example responses

> 200 Response

<h3 id="changeuser-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="changeuser-responseschema">Response Schema</h3>

<aside class="success">
This operation does not require authentication
</aside>

## deleteUser

<a id="opIddeleteUser"></a>

> Code samples

```shell
# You can also use wget
curl -X DELETE http://localhost:8080/api/user \
  -H 'Accept: */*' \
  -H 'Authorization: string'

```

```http
DELETE http://localhost:8080/api/user HTTP/1.1
Host: localhost:8080
Accept: */*
Authorization: string

```

```javascript

const headers = {
  'Accept':'*/*',
  'Authorization':'string'
};

fetch('http://localhost:8080/api/user',
{
  method: 'DELETE',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*',
  'Authorization' => 'string'
}

result = RestClient.delete 'http://localhost:8080/api/user',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*',
  'Authorization': 'string'
}

r = requests.delete('http://localhost:8080/api/user', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
    'Authorization' => 'string',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('DELETE','http://localhost:8080/api/user', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/user");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("DELETE");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
        "Authorization": []string{"string"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("DELETE", "http://localhost:8080/api/user", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`DELETE /api/user`

<h3 id="deleteuser-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|Authorization|header|string|true|none|

> Example responses

> 200 Response

<h3 id="deleteuser-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="deleteuser-responseschema">Response Schema</h3>

<aside class="success">
This operation does not require authentication
</aside>

<h1 id="openapi-definition-routine-controller">routine-controller</h1>

## getRoutine

<a id="opIdgetRoutine"></a>

> Code samples

```shell
# You can also use wget
curl -X GET http://localhost:8080/api/routine/{id} \
  -H 'Accept: */*' \
  -H 'Authorization: string'

```

```http
GET http://localhost:8080/api/routine/{id} HTTP/1.1
Host: localhost:8080
Accept: */*
Authorization: string

```

```javascript

const headers = {
  'Accept':'*/*',
  'Authorization':'string'
};

fetch('http://localhost:8080/api/routine/{id}',
{
  method: 'GET',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*',
  'Authorization' => 'string'
}

result = RestClient.get 'http://localhost:8080/api/routine/{id}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*',
  'Authorization': 'string'
}

r = requests.get('http://localhost:8080/api/routine/{id}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
    'Authorization' => 'string',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('GET','http://localhost:8080/api/routine/{id}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/routine/{id}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("GET");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
        "Authorization": []string{"string"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("GET", "http://localhost:8080/api/routine/{id}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`GET /api/routine/{id}`

<h3 id="getroutine-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|id|path|string|true|none|
|Authorization|header|string|true|none|

> Example responses

> 200 Response

<h3 id="getroutine-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="getroutine-responseschema">Response Schema</h3>

<aside class="success">
This operation does not require authentication
</aside>

## changeRoutine

<a id="opIdchangeRoutine"></a>

> Code samples

```shell
# You can also use wget
curl -X PUT http://localhost:8080/api/routine/{id} \
  -H 'Content-Type: application/json' \
  -H 'Accept: */*' \
  -H 'Authorization: string'

```

```http
PUT http://localhost:8080/api/routine/{id} HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: */*
Authorization: string

```

```javascript
const inputBody = '{
  "id": 0,
  "name": "string",
  "actions": [
    {
      "id": 0,
      "device_id": "string",
      "device_name": "string",
      "action": "string"
    }
  ],
  "trigger": {
    "type": "string",
    "value": "string"
  },
  "state": true
}';
const headers = {
  'Content-Type':'application/json',
  'Accept':'*/*',
  'Authorization':'string'
};

fetch('http://localhost:8080/api/routine/{id}',
{
  method: 'PUT',
  body: inputBody,
  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Content-Type' => 'application/json',
  'Accept' => '*/*',
  'Authorization' => 'string'
}

result = RestClient.put 'http://localhost:8080/api/routine/{id}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Content-Type': 'application/json',
  'Accept': '*/*',
  'Authorization': 'string'
}

r = requests.put('http://localhost:8080/api/routine/{id}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Content-Type' => 'application/json',
    'Accept' => '*/*',
    'Authorization' => 'string',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('PUT','http://localhost:8080/api/routine/{id}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/routine/{id}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("PUT");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Content-Type": []string{"application/json"},
        "Accept": []string{"*/*"},
        "Authorization": []string{"string"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("PUT", "http://localhost:8080/api/routine/{id}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`PUT /api/routine/{id}`

> Body parameter

```json
{
  "id": 0,
  "name": "string",
  "actions": [
    {
      "id": 0,
      "device_id": "string",
      "device_name": "string",
      "action": "string"
    }
  ],
  "trigger": {
    "type": "string",
    "value": "string"
  },
  "state": true
}
```

<h3 id="changeroutine-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|id|path|string|true|none|
|Authorization|header|string|true|none|
|body|body|[RoutineDTO](#schemaroutinedto)|true|none|

> Example responses

> 200 Response

<h3 id="changeroutine-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="changeroutine-responseschema">Response Schema</h3>

<aside class="success">
This operation does not require authentication
</aside>

## deleteRoutine

<a id="opIddeleteRoutine"></a>

> Code samples

```shell
# You can also use wget
curl -X DELETE http://localhost:8080/api/routine/{id} \
  -H 'Accept: */*' \
  -H 'Authorization: string'

```

```http
DELETE http://localhost:8080/api/routine/{id} HTTP/1.1
Host: localhost:8080
Accept: */*
Authorization: string

```

```javascript

const headers = {
  'Accept':'*/*',
  'Authorization':'string'
};

fetch('http://localhost:8080/api/routine/{id}',
{
  method: 'DELETE',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*',
  'Authorization' => 'string'
}

result = RestClient.delete 'http://localhost:8080/api/routine/{id}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*',
  'Authorization': 'string'
}

r = requests.delete('http://localhost:8080/api/routine/{id}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
    'Authorization' => 'string',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('DELETE','http://localhost:8080/api/routine/{id}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/routine/{id}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("DELETE");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
        "Authorization": []string{"string"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("DELETE", "http://localhost:8080/api/routine/{id}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`DELETE /api/routine/{id}`

<h3 id="deleteroutine-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|id|path|string|true|none|
|Authorization|header|string|true|none|

> Example responses

> 200 Response

<h3 id="deleteroutine-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="deleteroutine-responseschema">Response Schema</h3>

<aside class="success">
This operation does not require authentication
</aside>

## getAllRoutines

<a id="opIdgetAllRoutines"></a>

> Code samples

```shell
# You can also use wget
curl -X GET http://localhost:8080/api/routine \
  -H 'Accept: */*' \
  -H 'Authorization: string'

```

```http
GET http://localhost:8080/api/routine HTTP/1.1
Host: localhost:8080
Accept: */*
Authorization: string

```

```javascript

const headers = {
  'Accept':'*/*',
  'Authorization':'string'
};

fetch('http://localhost:8080/api/routine',
{
  method: 'GET',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*',
  'Authorization' => 'string'
}

result = RestClient.get 'http://localhost:8080/api/routine',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*',
  'Authorization': 'string'
}

r = requests.get('http://localhost:8080/api/routine', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
    'Authorization' => 'string',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('GET','http://localhost:8080/api/routine', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/routine");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("GET");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
        "Authorization": []string{"string"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("GET", "http://localhost:8080/api/routine", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`GET /api/routine`

<h3 id="getallroutines-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|Authorization|header|string|true|none|

> Example responses

> 200 Response

<h3 id="getallroutines-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="getallroutines-responseschema">Response Schema</h3>

<aside class="success">
This operation does not require authentication
</aside>

## createRoutine

<a id="opIdcreateRoutine"></a>

> Code samples

```shell
# You can also use wget
curl -X POST http://localhost:8080/api/routine \
  -H 'Content-Type: application/json' \
  -H 'Accept: */*' \
  -H 'Authorization: string'

```

```http
POST http://localhost:8080/api/routine HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: */*
Authorization: string

```

```javascript
const inputBody = '{
  "id": 0,
  "name": "string",
  "actions": [
    {
      "id": 0,
      "device_id": "string",
      "device_name": "string",
      "action": "string"
    }
  ],
  "trigger": {
    "type": "string",
    "value": "string"
  },
  "state": true
}';
const headers = {
  'Content-Type':'application/json',
  'Accept':'*/*',
  'Authorization':'string'
};

fetch('http://localhost:8080/api/routine',
{
  method: 'POST',
  body: inputBody,
  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Content-Type' => 'application/json',
  'Accept' => '*/*',
  'Authorization' => 'string'
}

result = RestClient.post 'http://localhost:8080/api/routine',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Content-Type': 'application/json',
  'Accept': '*/*',
  'Authorization': 'string'
}

r = requests.post('http://localhost:8080/api/routine', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Content-Type' => 'application/json',
    'Accept' => '*/*',
    'Authorization' => 'string',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('POST','http://localhost:8080/api/routine', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/routine");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("POST");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Content-Type": []string{"application/json"},
        "Accept": []string{"*/*"},
        "Authorization": []string{"string"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("POST", "http://localhost:8080/api/routine", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`POST /api/routine`

> Body parameter

```json
{
  "id": 0,
  "name": "string",
  "actions": [
    {
      "id": 0,
      "device_id": "string",
      "device_name": "string",
      "action": "string"
    }
  ],
  "trigger": {
    "type": "string",
    "value": "string"
  },
  "state": true
}
```

<h3 id="createroutine-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|Authorization|header|string|true|none|
|body|body|[RoutineDTO](#schemaroutinedto)|true|none|

> Example responses

> 200 Response

<h3 id="createroutine-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="createroutine-responseschema">Response Schema</h3>

<aside class="success">
This operation does not require authentication
</aside>

## switchRoutine

<a id="opIdswitchRoutine"></a>

> Code samples

```shell
# You can also use wget
curl -X POST http://localhost:8080/api/routine/{id}/{state}?state=string&id=string \
  -H 'Accept: */*' \
  -H 'Authorization: string'

```

```http
POST http://localhost:8080/api/routine/{id}/{state}?state=string&id=string HTTP/1.1
Host: localhost:8080
Accept: */*
Authorization: string

```

```javascript

const headers = {
  'Accept':'*/*',
  'Authorization':'string'
};

fetch('http://localhost:8080/api/routine/{id}/{state}?state=string&id=string',
{
  method: 'POST',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*',
  'Authorization' => 'string'
}

result = RestClient.post 'http://localhost:8080/api/routine/{id}/{state}',
  params: {
  'state' => 'string',
'id' => 'string'
}, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*',
  'Authorization': 'string'
}

r = requests.post('http://localhost:8080/api/routine/{id}/{state}', params={
  'state': 'string',  'id': 'string'
}, headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
    'Authorization' => 'string',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('POST','http://localhost:8080/api/routine/{id}/{state}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/routine/{id}/{state}?state=string&id=string");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("POST");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
        "Authorization": []string{"string"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("POST", "http://localhost:8080/api/routine/{id}/{state}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`POST /api/routine/{id}/{state}`

<h3 id="switchroutine-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|Authorization|header|string|true|none|
|state|query|string|true|none|
|id|query|string|true|none|

> Example responses

> 200 Response

<h3 id="switchroutine-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="switchroutine-responseschema">Response Schema</h3>

<aside class="success">
This operation does not require authentication
</aside>

<h1 id="openapi-definition-room-controller">room-controller</h1>

## getRoom

<a id="opIdgetRoom"></a>

> Code samples

```shell
# You can also use wget
curl -X GET http://localhost:8080/api/room/{id} \
  -H 'Accept: */*' \
  -H 'Authorization: string'

```

```http
GET http://localhost:8080/api/room/{id} HTTP/1.1
Host: localhost:8080
Accept: */*
Authorization: string

```

```javascript

const headers = {
  'Accept':'*/*',
  'Authorization':'string'
};

fetch('http://localhost:8080/api/room/{id}',
{
  method: 'GET',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*',
  'Authorization' => 'string'
}

result = RestClient.get 'http://localhost:8080/api/room/{id}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*',
  'Authorization': 'string'
}

r = requests.get('http://localhost:8080/api/room/{id}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
    'Authorization' => 'string',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('GET','http://localhost:8080/api/room/{id}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/room/{id}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("GET");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
        "Authorization": []string{"string"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("GET", "http://localhost:8080/api/room/{id}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`GET /api/room/{id}`

<h3 id="getroom-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|id|path|integer(int32)|true|none|
|Authorization|header|string|true|none|

> Example responses

> 200 Response

<h3 id="getroom-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="getroom-responseschema">Response Schema</h3>

<aside class="success">
This operation does not require authentication
</aside>

## changeRoom

<a id="opIdchangeRoom"></a>

> Code samples

```shell
# You can also use wget
curl -X PUT http://localhost:8080/api/room/{id} \
  -H 'Content-Type: application/json' \
  -H 'Accept: */*' \
  -H 'Authorization: string'

```

```http
PUT http://localhost:8080/api/room/{id} HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: */*
Authorization: string

```

```javascript
const inputBody = '{
  "new-value": "string",
  "field": "string"
}';
const headers = {
  'Content-Type':'application/json',
  'Accept':'*/*',
  'Authorization':'string'
};

fetch('http://localhost:8080/api/room/{id}',
{
  method: 'PUT',
  body: inputBody,
  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Content-Type' => 'application/json',
  'Accept' => '*/*',
  'Authorization' => 'string'
}

result = RestClient.put 'http://localhost:8080/api/room/{id}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Content-Type': 'application/json',
  'Accept': '*/*',
  'Authorization': 'string'
}

r = requests.put('http://localhost:8080/api/room/{id}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Content-Type' => 'application/json',
    'Accept' => '*/*',
    'Authorization' => 'string',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('PUT','http://localhost:8080/api/room/{id}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/room/{id}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("PUT");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Content-Type": []string{"application/json"},
        "Accept": []string{"*/*"},
        "Authorization": []string{"string"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("PUT", "http://localhost:8080/api/room/{id}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`PUT /api/room/{id}`

> Body parameter

```json
{
  "new-value": "string",
  "field": "string"
}
```

<h3 id="changeroom-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|Authorization|header|string|true|none|
|id|path|integer(int32)|true|none|
|body|body|[ChangeRequest](#schemachangerequest)|true|none|

> Example responses

> 200 Response

<h3 id="changeroom-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="changeroom-responseschema">Response Schema</h3>

<aside class="success">
This operation does not require authentication
</aside>

## deleteRoom

<a id="opIddeleteRoom"></a>

> Code samples

```shell
# You can also use wget
curl -X DELETE http://localhost:8080/api/room/{id} \
  -H 'Accept: */*' \
  -H 'Authorization: string'

```

```http
DELETE http://localhost:8080/api/room/{id} HTTP/1.1
Host: localhost:8080
Accept: */*
Authorization: string

```

```javascript

const headers = {
  'Accept':'*/*',
  'Authorization':'string'
};

fetch('http://localhost:8080/api/room/{id}',
{
  method: 'DELETE',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*',
  'Authorization' => 'string'
}

result = RestClient.delete 'http://localhost:8080/api/room/{id}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*',
  'Authorization': 'string'
}

r = requests.delete('http://localhost:8080/api/room/{id}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
    'Authorization' => 'string',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('DELETE','http://localhost:8080/api/room/{id}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/room/{id}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("DELETE");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
        "Authorization": []string{"string"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("DELETE", "http://localhost:8080/api/room/{id}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`DELETE /api/room/{id}`

<h3 id="deleteroom-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|id|path|integer(int32)|true|none|
|Authorization|header|string|true|none|

> Example responses

> 200 Response

<h3 id="deleteroom-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="deleteroom-responseschema">Response Schema</h3>

<aside class="success">
This operation does not require authentication
</aside>

## getAllRooms

<a id="opIdgetAllRooms"></a>

> Code samples

```shell
# You can also use wget
curl -X GET http://localhost:8080/api/room \
  -H 'Accept: */*' \
  -H 'Authorization: string'

```

```http
GET http://localhost:8080/api/room HTTP/1.1
Host: localhost:8080
Accept: */*
Authorization: string

```

```javascript

const headers = {
  'Accept':'*/*',
  'Authorization':'string'
};

fetch('http://localhost:8080/api/room',
{
  method: 'GET',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*',
  'Authorization' => 'string'
}

result = RestClient.get 'http://localhost:8080/api/room',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*',
  'Authorization': 'string'
}

r = requests.get('http://localhost:8080/api/room', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
    'Authorization' => 'string',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('GET','http://localhost:8080/api/room', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/room");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("GET");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
        "Authorization": []string{"string"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("GET", "http://localhost:8080/api/room", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`GET /api/room`

<h3 id="getallrooms-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|Authorization|header|string|true|none|

> Example responses

> 200 Response

<h3 id="getallrooms-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="getallrooms-responseschema">Response Schema</h3>

<aside class="success">
This operation does not require authentication
</aside>

## createRoom

<a id="opIdcreateRoom"></a>

> Code samples

```shell
# You can also use wget
curl -X POST http://localhost:8080/api/room \
  -H 'Content-Type: application/json' \
  -H 'Accept: */*' \
  -H 'Authorization: string'

```

```http
POST http://localhost:8080/api/room HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: */*
Authorization: string

```

```javascript
const inputBody = '{
  "room_id": "string",
  "name": "string"
}';
const headers = {
  'Content-Type':'application/json',
  'Accept':'*/*',
  'Authorization':'string'
};

fetch('http://localhost:8080/api/room',
{
  method: 'POST',
  body: inputBody,
  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Content-Type' => 'application/json',
  'Accept' => '*/*',
  'Authorization' => 'string'
}

result = RestClient.post 'http://localhost:8080/api/room',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Content-Type': 'application/json',
  'Accept': '*/*',
  'Authorization': 'string'
}

r = requests.post('http://localhost:8080/api/room', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Content-Type' => 'application/json',
    'Accept' => '*/*',
    'Authorization' => 'string',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('POST','http://localhost:8080/api/room', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/room");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("POST");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Content-Type": []string{"application/json"},
        "Accept": []string{"*/*"},
        "Authorization": []string{"string"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("POST", "http://localhost:8080/api/room", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`POST /api/room`

> Body parameter

```json
{
  "room_id": "string",
  "name": "string"
}
```

<h3 id="createroom-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|Authorization|header|string|true|none|
|body|body|[RoomDTO](#schemaroomdto)|true|none|

> Example responses

> 200 Response

<h3 id="createroom-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="createroom-responseschema">Response Schema</h3>

<aside class="success">
This operation does not require authentication
</aside>

<h1 id="openapi-definition-device-controller">device-controller</h1>

## getDevice

<a id="opIdgetDevice"></a>

> Code samples

```shell
# You can also use wget
curl -X GET http://localhost:8080/api/device/{id} \
  -H 'Accept: */*' \
  -H 'Authorization: string'

```

```http
GET http://localhost:8080/api/device/{id} HTTP/1.1
Host: localhost:8080
Accept: */*
Authorization: string

```

```javascript

const headers = {
  'Accept':'*/*',
  'Authorization':'string'
};

fetch('http://localhost:8080/api/device/{id}',
{
  method: 'GET',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*',
  'Authorization' => 'string'
}

result = RestClient.get 'http://localhost:8080/api/device/{id}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*',
  'Authorization': 'string'
}

r = requests.get('http://localhost:8080/api/device/{id}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
    'Authorization' => 'string',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('GET','http://localhost:8080/api/device/{id}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/device/{id}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("GET");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
        "Authorization": []string{"string"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("GET", "http://localhost:8080/api/device/{id}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`GET /api/device/{id}`

<h3 id="getdevice-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|id|path|string|true|none|
|Authorization|header|string|true|none|

> Example responses

> 200 Response

<h3 id="getdevice-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="getdevice-responseschema">Response Schema</h3>

<aside class="success">
This operation does not require authentication
</aside>

## changeDevice

<a id="opIdchangeDevice"></a>

> Code samples

```shell
# You can also use wget
curl -X PUT http://localhost:8080/api/device/{id} \
  -H 'Content-Type: application/json' \
  -H 'Accept: */*' \
  -H 'Authorization: string'

```

```http
PUT http://localhost:8080/api/device/{id} HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: */*
Authorization: string

```

```javascript
const inputBody = '{
  "new-value": "string",
  "field": "string"
}';
const headers = {
  'Content-Type':'application/json',
  'Accept':'*/*',
  'Authorization':'string'
};

fetch('http://localhost:8080/api/device/{id}',
{
  method: 'PUT',
  body: inputBody,
  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Content-Type' => 'application/json',
  'Accept' => '*/*',
  'Authorization' => 'string'
}

result = RestClient.put 'http://localhost:8080/api/device/{id}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Content-Type': 'application/json',
  'Accept': '*/*',
  'Authorization': 'string'
}

r = requests.put('http://localhost:8080/api/device/{id}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Content-Type' => 'application/json',
    'Accept' => '*/*',
    'Authorization' => 'string',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('PUT','http://localhost:8080/api/device/{id}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/device/{id}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("PUT");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Content-Type": []string{"application/json"},
        "Accept": []string{"*/*"},
        "Authorization": []string{"string"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("PUT", "http://localhost:8080/api/device/{id}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`PUT /api/device/{id}`

> Body parameter

```json
{
  "new-value": "string",
  "field": "string"
}
```

<h3 id="changedevice-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|Authorization|header|string|true|none|
|id|path|string|true|none|
|body|body|[ChangeRequest](#schemachangerequest)|true|none|

> Example responses

> 200 Response

<h3 id="changedevice-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="changedevice-responseschema">Response Schema</h3>

<aside class="success">
This operation does not require authentication
</aside>

## deleteDevice

<a id="opIddeleteDevice"></a>

> Code samples

```shell
# You can also use wget
curl -X DELETE http://localhost:8080/api/device/{id} \
  -H 'Accept: */*' \
  -H 'Authorization: string'

```

```http
DELETE http://localhost:8080/api/device/{id} HTTP/1.1
Host: localhost:8080
Accept: */*
Authorization: string

```

```javascript

const headers = {
  'Accept':'*/*',
  'Authorization':'string'
};

fetch('http://localhost:8080/api/device/{id}',
{
  method: 'DELETE',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*',
  'Authorization' => 'string'
}

result = RestClient.delete 'http://localhost:8080/api/device/{id}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*',
  'Authorization': 'string'
}

r = requests.delete('http://localhost:8080/api/device/{id}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
    'Authorization' => 'string',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('DELETE','http://localhost:8080/api/device/{id}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/device/{id}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("DELETE");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
        "Authorization": []string{"string"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("DELETE", "http://localhost:8080/api/device/{id}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`DELETE /api/device/{id}`

<h3 id="deletedevice-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|id|path|string|true|none|
|Authorization|header|string|true|none|

> Example responses

> 200 Response

<h3 id="deletedevice-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="deletedevice-responseschema">Response Schema</h3>

<aside class="success">
This operation does not require authentication
</aside>

## getAllDevices

<a id="opIdgetAllDevices"></a>

> Code samples

```shell
# You can also use wget
curl -X GET http://localhost:8080/api/device \
  -H 'Accept: */*' \
  -H 'Authorization: string'

```

```http
GET http://localhost:8080/api/device HTTP/1.1
Host: localhost:8080
Accept: */*
Authorization: string

```

```javascript

const headers = {
  'Accept':'*/*',
  'Authorization':'string'
};

fetch('http://localhost:8080/api/device',
{
  method: 'GET',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*',
  'Authorization' => 'string'
}

result = RestClient.get 'http://localhost:8080/api/device',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*',
  'Authorization': 'string'
}

r = requests.get('http://localhost:8080/api/device', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
    'Authorization' => 'string',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('GET','http://localhost:8080/api/device', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/device");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("GET");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
        "Authorization": []string{"string"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("GET", "http://localhost:8080/api/device", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`GET /api/device`

<h3 id="getalldevices-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|Authorization|header|string|true|none|

> Example responses

> 200 Response

<h3 id="getalldevices-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="getalldevices-responseschema">Response Schema</h3>

<aside class="success">
This operation does not require authentication
</aside>

## createDevice

<a id="opIdcreateDevice"></a>

> Code samples

```shell
# You can also use wget
curl -X POST http://localhost:8080/api/device \
  -H 'Content-Type: application/json' \
  -H 'Accept: */*' \
  -H 'Authorization: string'

```

```http
POST http://localhost:8080/api/device HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: */*
Authorization: string

```

```javascript
const inputBody = '{
  "device_id": "string",
  "name": "string",
  "typeID": 0,
  "type": "string",
  "typeIcon": "string",
  "location": 0
}';
const headers = {
  'Content-Type':'application/json',
  'Accept':'*/*',
  'Authorization':'string'
};

fetch('http://localhost:8080/api/device',
{
  method: 'POST',
  body: inputBody,
  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Content-Type' => 'application/json',
  'Accept' => '*/*',
  'Authorization' => 'string'
}

result = RestClient.post 'http://localhost:8080/api/device',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Content-Type': 'application/json',
  'Accept': '*/*',
  'Authorization': 'string'
}

r = requests.post('http://localhost:8080/api/device', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Content-Type' => 'application/json',
    'Accept' => '*/*',
    'Authorization' => 'string',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('POST','http://localhost:8080/api/device', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/device");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("POST");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Content-Type": []string{"application/json"},
        "Accept": []string{"*/*"},
        "Authorization": []string{"string"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("POST", "http://localhost:8080/api/device", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`POST /api/device`

> Body parameter

```json
{
  "device_id": "string",
  "name": "string",
  "typeID": 0,
  "type": "string",
  "typeIcon": "string",
  "location": 0
}
```

<h3 id="createdevice-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|Authorization|header|string|true|none|
|body|body|[DeviceDTO](#schemadevicedto)|true|none|

> Example responses

> 200 Response

<h3 id="createdevice-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="createdevice-responseschema">Response Schema</h3>

<aside class="success">
This operation does not require authentication
</aside>

## setDeviceSwitchStatus

<a id="opIdsetDeviceSwitchStatus"></a>

> Code samples

```shell
# You can also use wget
curl -X POST http://localhost:8080/api/device/{id}/switch/{action} \
  -H 'Accept: */*' \
  -H 'Authorization: string'

```

```http
POST http://localhost:8080/api/device/{id}/switch/{action} HTTP/1.1
Host: localhost:8080
Accept: */*
Authorization: string

```

```javascript

const headers = {
  'Accept':'*/*',
  'Authorization':'string'
};

fetch('http://localhost:8080/api/device/{id}/switch/{action}',
{
  method: 'POST',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*',
  'Authorization' => 'string'
}

result = RestClient.post 'http://localhost:8080/api/device/{id}/switch/{action}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*',
  'Authorization': 'string'
}

r = requests.post('http://localhost:8080/api/device/{id}/switch/{action}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
    'Authorization' => 'string',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('POST','http://localhost:8080/api/device/{id}/switch/{action}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/device/{id}/switch/{action}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("POST");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
        "Authorization": []string{"string"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("POST", "http://localhost:8080/api/device/{id}/switch/{action}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`POST /api/device/{id}/switch/{action}`

<h3 id="setdeviceswitchstatus-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|id|path|string|true|none|
|action|path|string|true|none|
|Authorization|header|string|true|none|

> Example responses

> 200 Response

<h3 id="setdeviceswitchstatus-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="setdeviceswitchstatus-responseschema">Response Schema</h3>

<aside class="success">
This operation does not require authentication
</aside>

## GetHealth

<a id="opIdGetHealth"></a>

> Code samples

```shell
# You can also use wget
curl -X GET http://localhost:8080/api/device/{id}/health-check \
  -H 'Accept: */*' \
  -H 'Authorization: string'

```

```http
GET http://localhost:8080/api/device/{id}/health-check HTTP/1.1
Host: localhost:8080
Accept: */*
Authorization: string

```

```javascript

const headers = {
  'Accept':'*/*',
  'Authorization':'string'
};

fetch('http://localhost:8080/api/device/{id}/health-check',
{
  method: 'GET',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*',
  'Authorization' => 'string'
}

result = RestClient.get 'http://localhost:8080/api/device/{id}/health-check',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*',
  'Authorization': 'string'
}

r = requests.get('http://localhost:8080/api/device/{id}/health-check', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
    'Authorization' => 'string',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('GET','http://localhost:8080/api/device/{id}/health-check', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/device/{id}/health-check");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("GET");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
        "Authorization": []string{"string"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("GET", "http://localhost:8080/api/device/{id}/health-check", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`GET /api/device/{id}/health-check`

<h3 id="gethealth-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|id|path|string|true|none|
|Authorization|header|string|true|none|

> Example responses

> 200 Response

<h3 id="gethealth-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="gethealth-responseschema">Response Schema</h3>

<aside class="success">
This operation does not require authentication
</aside>

## getAllSmartThingsDevices

<a id="opIdgetAllSmartThingsDevices"></a>

> Code samples

```shell
# You can also use wget
curl -X GET http://localhost:8080/api/device/smartthings \
  -H 'Accept: */*' \
  -H 'Authorization: string'

```

```http
GET http://localhost:8080/api/device/smartthings HTTP/1.1
Host: localhost:8080
Accept: */*
Authorization: string

```

```javascript

const headers = {
  'Accept':'*/*',
  'Authorization':'string'
};

fetch('http://localhost:8080/api/device/smartthings',
{
  method: 'GET',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*',
  'Authorization' => 'string'
}

result = RestClient.get 'http://localhost:8080/api/device/smartthings',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*',
  'Authorization': 'string'
}

r = requests.get('http://localhost:8080/api/device/smartthings', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
    'Authorization' => 'string',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('GET','http://localhost:8080/api/device/smartthings', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/device/smartthings");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("GET");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
        "Authorization": []string{"string"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("GET", "http://localhost:8080/api/device/smartthings", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`GET /api/device/smartthings`

<h3 id="getallsmartthingsdevices-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|Authorization|header|string|true|none|

> Example responses

> 200 Response

<h3 id="getallsmartthingsdevices-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="getallsmartthingsdevices-responseschema">Response Schema</h3>

<aside class="success">
This operation does not require authentication
</aside>

## getTypes

<a id="opIdgetTypes"></a>

> Code samples

```shell
# You can also use wget
curl -X GET http://localhost:8080/api/device/device-type \
  -H 'Accept: */*' \
  -H 'Authorization: string'

```

```http
GET http://localhost:8080/api/device/device-type HTTP/1.1
Host: localhost:8080
Accept: */*
Authorization: string

```

```javascript

const headers = {
  'Accept':'*/*',
  'Authorization':'string'
};

fetch('http://localhost:8080/api/device/device-type',
{
  method: 'GET',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*',
  'Authorization' => 'string'
}

result = RestClient.get 'http://localhost:8080/api/device/device-type',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*',
  'Authorization': 'string'
}

r = requests.get('http://localhost:8080/api/device/device-type', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
    'Authorization' => 'string',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('GET','http://localhost:8080/api/device/device-type', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/device/device-type");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("GET");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
        "Authorization": []string{"string"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("GET", "http://localhost:8080/api/device/device-type", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`GET /api/device/device-type`

<h3 id="gettypes-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|Authorization|header|string|true|none|

> Example responses

> 200 Response

<h3 id="gettypes-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="gettypes-responseschema">Response Schema</h3>

<aside class="success">
This operation does not require authentication
</aside>

<h1 id="openapi-definition-auth-controller">auth-controller</h1>

## getAuth

<a id="opIdgetAuth"></a>

> Code samples

```shell
# You can also use wget
curl -X GET http://localhost:8080/api/auth \
  -H 'Accept: */*'

```

```http
GET http://localhost:8080/api/auth HTTP/1.1
Host: localhost:8080
Accept: */*

```

```javascript

const headers = {
  'Accept':'*/*'
};

fetch('http://localhost:8080/api/auth',
{
  method: 'GET',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*'
}

result = RestClient.get 'http://localhost:8080/api/auth',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*'
}

r = requests.get('http://localhost:8080/api/auth', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('GET','http://localhost:8080/api/auth', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/auth");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("GET");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("GET", "http://localhost:8080/api/auth", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`GET /api/auth`

> Example responses

> 200 Response

<h3 id="getauth-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|string|

<aside class="success">
This operation does not require authentication
</aside>

## signIn

<a id="opIdsignIn"></a>

> Code samples

```shell
# You can also use wget
curl -X POST http://localhost:8080/api/auth \
  -H 'Content-Type: application/json' \
  -H 'Accept: */*'

```

```http
POST http://localhost:8080/api/auth HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: */*

```

```javascript
const inputBody = '{
  "email": "string",
  "password": "string"
}';
const headers = {
  'Content-Type':'application/json',
  'Accept':'*/*'
};

fetch('http://localhost:8080/api/auth',
{
  method: 'POST',
  body: inputBody,
  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Content-Type' => 'application/json',
  'Accept' => '*/*'
}

result = RestClient.post 'http://localhost:8080/api/auth',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Content-Type': 'application/json',
  'Accept': '*/*'
}

r = requests.post('http://localhost:8080/api/auth', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Content-Type' => 'application/json',
    'Accept' => '*/*',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('POST','http://localhost:8080/api/auth', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/auth");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("POST");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Content-Type": []string{"application/json"},
        "Accept": []string{"*/*"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("POST", "http://localhost:8080/api/auth", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`POST /api/auth`

> Body parameter

```json
{
  "email": "string",
  "password": "string"
}
```

<h3 id="signin-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|body|body|[AuthMessage](#schemaauthmessage)|true|none|

> Example responses

> 200 Response

<h3 id="signin-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="signin-responseschema">Response Schema</h3>

<aside class="success">
This operation does not require authentication
</aside>

## validateToken

<a id="opIdvalidateToken"></a>

> Code samples

```shell
# You can also use wget
curl -X POST http://localhost:8080/api/auth/validate-token \
  -H 'Accept: */*' \
  -H 'Authorization: string'

```

```http
POST http://localhost:8080/api/auth/validate-token HTTP/1.1
Host: localhost:8080
Accept: */*
Authorization: string

```

```javascript

const headers = {
  'Accept':'*/*',
  'Authorization':'string'
};

fetch('http://localhost:8080/api/auth/validate-token',
{
  method: 'POST',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*',
  'Authorization' => 'string'
}

result = RestClient.post 'http://localhost:8080/api/auth/validate-token',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*',
  'Authorization': 'string'
}

r = requests.post('http://localhost:8080/api/auth/validate-token', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
    'Authorization' => 'string',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('POST','http://localhost:8080/api/auth/validate-token', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/auth/validate-token");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("POST");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
        "Authorization": []string{"string"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("POST", "http://localhost:8080/api/auth/validate-token", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`POST /api/auth/validate-token`

<h3 id="validatetoken-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|Authorization|header|string|true|none|

> Example responses

> 200 Response

<h3 id="validatetoken-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="validatetoken-responseschema">Response Schema</h3>

<aside class="success">
This operation does not require authentication
</aside>

## validateEmail

<a id="opIdvalidateEmail"></a>

> Code samples

```shell
# You can also use wget
curl -X POST http://localhost:8080/api/auth/validate-email/{validateToken} \
  -H 'Accept: */*'

```

```http
POST http://localhost:8080/api/auth/validate-email/{validateToken} HTTP/1.1
Host: localhost:8080
Accept: */*

```

```javascript

const headers = {
  'Accept':'*/*'
};

fetch('http://localhost:8080/api/auth/validate-email/{validateToken}',
{
  method: 'POST',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*'
}

result = RestClient.post 'http://localhost:8080/api/auth/validate-email/{validateToken}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*'
}

r = requests.post('http://localhost:8080/api/auth/validate-email/{validateToken}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('POST','http://localhost:8080/api/auth/validate-email/{validateToken}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/auth/validate-email/{validateToken}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("POST");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("POST", "http://localhost:8080/api/auth/validate-email/{validateToken}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`POST /api/auth/validate-email/{validateToken}`

<h3 id="validateemail-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|validateToken|path|string|true|none|

> Example responses

> 200 Response

<h3 id="validateemail-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="validateemail-responseschema">Response Schema</h3>

<aside class="success">
This operation does not require authentication
</aside>

## signUp

<a id="opIdsignUp"></a>

> Code samples

```shell
# You can also use wget
curl -X POST http://localhost:8080/api/auth/sign-up \
  -H 'Content-Type: application/json' \
  -H 'Accept: */*'

```

```http
POST http://localhost:8080/api/auth/sign-up HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: */*

```

```javascript
const inputBody = '{
  "firstName": "string",
  "lastName": "string",
  "email": "string",
  "password": "string",
  "pat": "string"
}';
const headers = {
  'Content-Type':'application/json',
  'Accept':'*/*'
};

fetch('http://localhost:8080/api/auth/sign-up',
{
  method: 'POST',
  body: inputBody,
  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Content-Type' => 'application/json',
  'Accept' => '*/*'
}

result = RestClient.post 'http://localhost:8080/api/auth/sign-up',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Content-Type': 'application/json',
  'Accept': '*/*'
}

r = requests.post('http://localhost:8080/api/auth/sign-up', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Content-Type' => 'application/json',
    'Accept' => '*/*',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('POST','http://localhost:8080/api/auth/sign-up', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/auth/sign-up");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("POST");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Content-Type": []string{"application/json"},
        "Accept": []string{"*/*"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("POST", "http://localhost:8080/api/auth/sign-up", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`POST /api/auth/sign-up`

> Body parameter

```json
{
  "firstName": "string",
  "lastName": "string",
  "email": "string",
  "password": "string",
  "pat": "string"
}
```

<h3 id="signup-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|body|body|[UserDTO](#schemauserdto)|true|none|

> Example responses

> 200 Response

<h3 id="signup-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="signup-responseschema">Response Schema</h3>

<aside class="success">
This operation does not require authentication
</aside>

# Schemas

<h2 id="tocS_ChangeRequest">ChangeRequest</h2>
<!-- backwards compatibility -->
<a id="schemachangerequest"></a>
<a id="schema_ChangeRequest"></a>
<a id="tocSchangerequest"></a>
<a id="tocschangerequest"></a>

```json
{
  "new-value": "string",
  "field": "string"
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|new-value|string|false|none|none|
|field|string|false|none|none|

<h2 id="tocS_ActionDTO">ActionDTO</h2>
<!-- backwards compatibility -->
<a id="schemaactiondto"></a>
<a id="schema_ActionDTO"></a>
<a id="tocSactiondto"></a>
<a id="tocsactiondto"></a>

```json
{
  "id": 0,
  "device_id": "string",
  "device_name": "string",
  "action": "string"
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|id|integer(int32)|false|none|none|
|device_id|string|false|none|none|
|device_name|string|false|none|none|
|action|string|false|none|none|

<h2 id="tocS_RoutineDTO">RoutineDTO</h2>
<!-- backwards compatibility -->
<a id="schemaroutinedto"></a>
<a id="schema_RoutineDTO"></a>
<a id="tocSroutinedto"></a>
<a id="tocsroutinedto"></a>

```json
{
  "id": 0,
  "name": "string",
  "actions": [
    {
      "id": 0,
      "device_id": "string",
      "device_name": "string",
      "action": "string"
    }
  ],
  "trigger": {
    "type": "string",
    "value": "string"
  },
  "state": true
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|id|integer(int32)|false|none|none|
|name|string|false|none|none|
|actions|[[ActionDTO](#schemaactiondto)]|false|none|none|
|trigger|[Trigger](#schematrigger)|false|none|none|
|state|boolean|false|none|none|

<h2 id="tocS_Trigger">Trigger</h2>
<!-- backwards compatibility -->
<a id="schematrigger"></a>
<a id="schema_Trigger"></a>
<a id="tocStrigger"></a>
<a id="tocstrigger"></a>

```json
{
  "type": "string",
  "value": "string"
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|type|string|false|none|none|
|value|string|false|none|none|

<h2 id="tocS_RoomDTO">RoomDTO</h2>
<!-- backwards compatibility -->
<a id="schemaroomdto"></a>
<a id="schema_RoomDTO"></a>
<a id="tocSroomdto"></a>
<a id="tocsroomdto"></a>

```json
{
  "room_id": "string",
  "name": "string"
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|room_id|string|false|none|none|
|name|string|false|none|none|

<h2 id="tocS_DeviceDTO">DeviceDTO</h2>
<!-- backwards compatibility -->
<a id="schemadevicedto"></a>
<a id="schema_DeviceDTO"></a>
<a id="tocSdevicedto"></a>
<a id="tocsdevicedto"></a>

```json
{
  "device_id": "string",
  "name": "string",
  "typeID": 0,
  "type": "string",
  "typeIcon": "string",
  "location": 0
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|device_id|string|false|none|none|
|name|string|false|none|none|
|typeID|integer(int32)|false|none|none|
|type|string|false|none|none|
|typeIcon|string|false|none|none|
|location|integer(int32)|false|none|none|

<h2 id="tocS_AuthMessage">AuthMessage</h2>
<!-- backwards compatibility -->
<a id="schemaauthmessage"></a>
<a id="schema_AuthMessage"></a>
<a id="tocSauthmessage"></a>
<a id="tocsauthmessage"></a>

```json
{
  "email": "string",
  "password": "string"
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|email|string|false|none|none|
|password|string|false|none|none|

<h2 id="tocS_UserDTO">UserDTO</h2>
<!-- backwards compatibility -->
<a id="schemauserdto"></a>
<a id="schema_UserDTO"></a>
<a id="tocSuserdto"></a>
<a id="tocsuserdto"></a>

```json
{
  "firstName": "string",
  "lastName": "string",
  "email": "string",
  "password": "string",
  "pat": "string"
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|firstName|string|false|none|none|
|lastName|string|false|none|none|
|email|string|false|none|none|
|password|string|false|none|none|
|pat|string|false|none|none|



<h2 class="code-line" data-line-start=303 data-line-end=304 ><a id="Contributers"></a>Contributors</h2>
- <a href="https://github.com/DrSommer20">Tim Sommer</a> - Backend Developer<br>
- <a href="https://github.com/paulib24">Paula Bauer</a> - Frontend Developer / Designer<br>
- <a href="https://github.com/Chris-laws">Chris David Kaufmann</a> - Frontend Developer / Designer<br> <br>

<a href="https://github.com/DrSommer20/SmartHomeDashboard/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=DrSommer20/SmartHomeDashboard" />
</a>

<h2 class="code-line" data-line-start=303 data-line-end=304 ><a id="License"></a>License</h2>
This project is licensed under the Apache 2.0 License.
