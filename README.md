<h1 class="code-line" data-line-start=0 data-line-end=1 ><a id="Smart_Home_Dashboard_0"></a>Smart Home Dashboard</h1>
<h2 class="code-line" data-line-start=2 data-line-end=3 ><a id="Overview_2"></a>Overview</h2>
<p class="has-line-data" data-line-start="4" data-line-end="5">The <strong>Smart Home Dashboard</strong> is a RESTful API system designed for controlling, managing, and monitoring smart home devices, users, rooms, and routines. It provides a wide range of functionalities such as device control, user authentication, room management, and automation through routines.</p>
<h2 class="code-line" data-line-start=6 data-line-end=7 ><a id="API_Endpoints_6"></a>API Endpoints</h2>
<p class="has-line-data" data-line-start="8" data-line-end="9">The API is divided into several sections, each handling a specific aspect of the smart home system:</p>
<h3 class="code-line" data-line-start=10 data-line-end=11 ><a id="1_Device_Endpoint_10"></a>1. <strong>Device Endpoint</strong></h3>
<p class="has-line-data" data-line-start="11" data-line-end="12">Manage smart devices (create, delete, update, and control).</p>
<ul>
<li class="has-line-data" data-line-start="13" data-line-end="30">
<p class="has-line-data" data-line-start="13" data-line-end="14"><strong>POST /device</strong></p>
<ul>
<li class="has-line-data" data-line-start="14" data-line-end="15">Create a new device.</li>
<li class="has-line-data" data-line-start="15" data-line-end="26"><strong>Data in:</strong><pre><code class="has-line-data" data-line-start="17" data-line-end="26" class="language-json">{
  "<span class="hljs-attribute">device</span>": <span class="hljs-value">{
    "<span class="hljs-attribute">device_id</span>": <span class="hljs-value"><span class="hljs-string">"unique-id"</span></span>,
    "<span class="hljs-attribute">name</span>": <span class="hljs-value"><span class="hljs-string">"Device Name"</span></span>,
    "<span class="hljs-attribute">type</span>": <span class="hljs-value"><span class="hljs-string">"outlet | sensor | etc."</span></span>,
    "<span class="hljs-attribute">location</span>": <span class="hljs-value"><span class="hljs-string">"Room Name"</span>
  </span>}
</span>}
</code></pre>
</li>
<li class="has-line-data" data-line-start="26" data-line-end="30"><strong>Data out:</strong>
<ul>
<li class="has-line-data" data-line-start="27" data-line-end="28">Success: <code>{ &quot;message&quot;: &quot;Device created&quot; }</code></li>
<li class="has-line-data" data-line-start="28" data-line-end="30">Failure: <code>{ &quot;reason&quot;: &quot;Wrong token&quot; }</code></li>
</ul>
</li>
</ul>
</li>
<li class="has-line-data" data-line-start="30" data-line-end="36">
<p class="has-line-data" data-line-start="30" data-line-end="31"><strong>DELETE /device/{device_id}</strong></p>
<ul>
<li class="has-line-data" data-line-start="31" data-line-end="32">Delete a device by ID.</li>
<li class="has-line-data" data-line-start="32" data-line-end="36"><strong>Data out:</strong>
<ul>
<li class="has-line-data" data-line-start="33" data-line-end="34">Success: <code>{ &quot;message&quot;: &quot;Deletion complete&quot; }</code></li>
<li class="has-line-data" data-line-start="34" data-line-end="36">Failure: <code>{ &quot;reason&quot;: &quot;Wrong token&quot; }</code></li>
</ul>
</li>
</ul>
</li>
<li class="has-line-data" data-line-start="36" data-line-end="51">
<p class="has-line-data" data-line-start="36" data-line-end="37"><strong>PUT /device/{device_id}</strong></p>
<ul>
<li class="has-line-data" data-line-start="37" data-line-end="38">Update an existing device.</li>
<li class="has-line-data" data-line-start="38" data-line-end="47"><strong>Data in:</strong><pre><code class="has-line-data" data-line-start="40" data-line-end="47" class="language-json">{
  "<span class="hljs-attribute">change</span>": <span class="hljs-value">{
    "<span class="hljs-attribute">new-value</span>": <span class="hljs-value"><span class="hljs-string">"New Value"</span></span>,
    "<span class="hljs-attribute">field</span>": <span class="hljs-value"><span class="hljs-string">"Field to update"</span>
  </span>}
</span>}
</code></pre>
</li>
<li class="has-line-data" data-line-start="47" data-line-end="51"><strong>Data out:</strong>
<ul>
<li class="has-line-data" data-line-start="48" data-line-end="49">Success: <code>{ &quot;message&quot;: &quot;Change complete&quot; }</code></li>
<li class="has-line-data" data-line-start="49" data-line-end="51">Failure: <code>{ &quot;reason&quot;: &quot;Wrong token&quot; }</code></li>
</ul>
</li>
</ul>
</li>
<li class="has-line-data" data-line-start="51" data-line-end="63">
<p class="has-line-data" data-line-start="51" data-line-end="52"><strong>GET /device</strong></p>
<ul>
<li class="has-line-data" data-line-start="52" data-line-end="53">Retrieve a list of all devices.</li>
<li class="has-line-data" data-line-start="53" data-line-end="63"><strong>Data out:</strong><pre><code class="has-line-data" data-line-start="55" data-line-end="62" class="language-json">{
  "<span class="hljs-attribute">devices</span>": <span class="hljs-value">[
    { "<span class="hljs-attribute">device_id</span>": <span class="hljs-value"><span class="hljs-string">"id"</span></span>, "<span class="hljs-attribute">name</span>": <span class="hljs-value"><span class="hljs-string">"name"</span></span>, "<span class="hljs-attribute">type</span>": <span class="hljs-value"><span class="hljs-string">"type"</span></span>, "<span class="hljs-attribute">location</span>": <span class="hljs-value"><span class="hljs-string">"location"</span></span>, "<span class="hljs-attribute">status</span>": <span class="hljs-value"><span class="hljs-string">"online"</span> </span>},
    ...
  ]
</span>}
</code></pre>
</li>
</ul>
</li>
<li class="has-line-data" data-line-start="63" data-line-end="76">
<p class="has-line-data" data-line-start="63" data-line-end="64"><strong>GET /device/{device_id}</strong></p>
<ul>
<li class="has-line-data" data-line-start="64" data-line-end="65">Retrieve a single device by ID.</li>
<li class="has-line-data" data-line-start="65" data-line-end="76"><strong>Data out:</strong><pre><code class="has-line-data" data-line-start="67" data-line-end="75" class="language-json">{
  "<span class="hljs-attribute">device_id</span>": <span class="hljs-value"><span class="hljs-string">"device01"</span></span>,
  "<span class="hljs-attribute">name</span>": <span class="hljs-value"><span class="hljs-string">"Device Name"</span></span>,
  "<span class="hljs-attribute">type</span>": <span class="hljs-value"><span class="hljs-string">"outlet"</span></span>,
  "<span class="hljs-attribute">location</span>": <span class="hljs-value"><span class="hljs-string">"Room Name"</span></span>,
  "<span class="hljs-attribute">status</span>": <span class="hljs-value"><span class="hljs-string">"online"</span>
</span>}
</code></pre>
</li>
</ul>
</li>
<li class="has-line-data" data-line-start="76" data-line-end="82">
<p class="has-line-data" data-line-start="76" data-line-end="77"><strong>POST /device/{device_id}/switch/{on/off}</strong></p>
<ul>
<li class="has-line-data" data-line-start="77" data-line-end="78">Turn a device on or off.</li>
<li class="has-line-data" data-line-start="78" data-line-end="82"><strong>Data out:</strong>
<ul>
<li class="has-line-data" data-line-start="79" data-line-end="80">Success: <code>{ &quot;message&quot;: &quot;Accepted&quot; }</code></li>
<li class="has-line-data" data-line-start="80" data-line-end="82">Failure: <code>{ &quot;reason&quot;: &quot;Connection Error&quot; }</code></li>
</ul>
</li>
</ul>
</li>
<li class="has-line-data" data-line-start="82" data-line-end="87">
<p class="has-line-data" data-line-start="82" data-line-end="83"><strong>GET /device/{device_id}/health-check</strong></p>
<ul>
<li class="has-line-data" data-line-start="83" data-line-end="84">Check the device’s online/offline status.</li>
<li class="has-line-data" data-line-start="84" data-line-end="87"><strong>Data out:</strong>
<ul>
<li class="has-line-data" data-line-start="85" data-line-end="87"><code>{ &quot;message&quot;: &quot;Online&quot; }</code> or <code>{ &quot;message&quot;: &quot;Offline&quot; }</code></li>
</ul>
</li>
</ul>
</li>
<li class="has-line-data" data-line-start="87" data-line-end="99">
<p class="has-line-data" data-line-start="87" data-line-end="88"><strong>GET /device/smartthings</strong></p>
<ul>
<li class="has-line-data" data-line-start="88" data-line-end="89">Retrieve all unassigned SmartThings devices.</li>
<li class="has-line-data" data-line-start="89" data-line-end="99"><strong>Data out:</strong><pre><code class="has-line-data" data-line-start="91" data-line-end="98" class="language-json">{
  "<span class="hljs-attribute">devices</span>": <span class="hljs-value">[
    { "<span class="hljs-attribute">device_id</span>": <span class="hljs-value"><span class="hljs-string">"id"</span></span>, "<span class="hljs-attribute">name</span>": <span class="hljs-value"><span class="hljs-string">"name"</span> </span>},
    ...
  ]
</span>}
</code></pre>
</li>
</ul>
</li>
</ul>
<h3 class="code-line" data-line-start=99 data-line-end=100 ><a id="2_Auth_Endpoint_99"></a>2. <strong>Auth Endpoint</strong></h3>
<p class="has-line-data" data-line-start="100" data-line-end="101">Handles user login, logout, and server health check.</p>
<ul>
<li class="has-line-data" data-line-start="102" data-line-end="115">
<p class="has-line-data" data-line-start="102" data-line-end="103"><strong>POST /auth</strong></p>
<ul>
<li class="has-line-data" data-line-start="103" data-line-end="104">Login to the system.</li>
<li class="has-line-data" data-line-start="104" data-line-end="111"><strong>Data in:</strong><pre><code class="has-line-data" data-line-start="106" data-line-end="111" class="language-json">{
  "<span class="hljs-attribute">email</span>": <span class="hljs-value"><span class="hljs-string">"user@example.com"</span></span>,
  "<span class="hljs-attribute">password</span>": <span class="hljs-value"><span class="hljs-string">"password"</span>
</span>}
</code></pre>
</li>
<li class="has-line-data" data-line-start="111" data-line-end="115"><strong>Data out:</strong>
<ul>
<li class="has-line-data" data-line-start="112" data-line-end="113">Success: <code>{ &quot;token&quot;: &quot;auth_token&quot; }</code></li>
<li class="has-line-data" data-line-start="113" data-line-end="115">Failure: <code>{ &quot;reason&quot;: &quot;Wrong credentials&quot; }</code></li>
</ul>
</li>
</ul>
</li>
<li class="has-line-data" data-line-start="115" data-line-end="121">
<p class="has-line-data" data-line-start="115" data-line-end="116"><strong>DELETE /auth</strong></p>
<ul>
<li class="has-line-data" data-line-start="116" data-line-end="117">Logout of the system.</li>
<li class="has-line-data" data-line-start="117" data-line-end="121"><strong>Data out:</strong>
<ul>
<li class="has-line-data" data-line-start="118" data-line-end="119">Success: <code>{ &quot;message&quot;: &quot;Sign out complete&quot; }</code></li>
<li class="has-line-data" data-line-start="119" data-line-end="121">Failure: <code>{ &quot;reason&quot;: &quot;Wrong token&quot; }</code></li>
</ul>
</li>
</ul>
</li>
<li class="has-line-data" data-line-start="121" data-line-end="126">
<p class="has-line-data" data-line-start="121" data-line-end="122"><strong>GET /auth/alive</strong></p>
<ul>
<li class="has-line-data" data-line-start="122" data-line-end="123">Check server status.</li>
<li class="has-line-data" data-line-start="123" data-line-end="126"><strong>Data out:</strong>
<ul>
<li class="has-line-data" data-line-start="124" data-line-end="126"><code>{ &quot;message&quot;: &quot;Alive&quot; }</code></li>
</ul>
</li>
</ul>
</li>
</ul>
<h3 class="code-line" data-line-start=126 data-line-end=127 ><a id="3_User_Endpoint_126"></a>3. <strong>User Endpoint</strong></h3>
<p class="has-line-data" data-line-start="127" data-line-end="128">Manage user accounts (create, delete, modify).</p>
<ul>
<li class="has-line-data" data-line-start="129" data-line-end="141">
<p class="has-line-data" data-line-start="129" data-line-end="130"><strong>GET /user</strong></p>
<ul>
<li class="has-line-data" data-line-start="130" data-line-end="131">Retrieve account details.</li>
<li class="has-line-data" data-line-start="131" data-line-end="141"><strong>Data out:</strong><pre><code class="has-line-data" data-line-start="133" data-line-end="140" class="language-json">{
  "<span class="hljs-attribute">firstName</span>": <span class="hljs-value"><span class="hljs-string">"Max"</span></span>,
  "<span class="hljs-attribute">lastName</span>": <span class="hljs-value"><span class="hljs-string">"Mustermann"</span></span>,
  "<span class="hljs-attribute">email</span>": <span class="hljs-value"><span class="hljs-string">"user@example.com"</span></span>,
  "<span class="hljs-attribute">password</span>": <span class="hljs-value"><span class="hljs-string">"password"</span>
</span>}
</code></pre>
</li>
</ul>
</li>
<li class="has-line-data" data-line-start="141" data-line-end="156">
<p class="has-line-data" data-line-start="141" data-line-end="142"><strong>POST /user</strong></p>
<ul>
<li class="has-line-data" data-line-start="142" data-line-end="143">Create a new account.</li>
<li class="has-line-data" data-line-start="143" data-line-end="152"><strong>Data in:</strong><pre><code class="has-line-data" data-line-start="145" data-line-end="152" class="language-json">{
  "<span class="hljs-attribute">firstName</span>": <span class="hljs-value"><span class="hljs-string">"Max"</span></span>,
  "<span class="hljs-attribute">lastName</span>": <span class="hljs-value"><span class="hljs-string">"Mustermann"</span></span>,
  "<span class="hljs-attribute">email</span>": <span class="hljs-value"><span class="hljs-string">"user@example.com"</span></span>,
  "<span class="hljs-attribute">password</span>": <span class="hljs-value"><span class="hljs-string">"password"</span>
</span>}
</code></pre>
</li>
<li class="has-line-data" data-line-start="152" data-line-end="156"><strong>Data out:</strong>
<ul>
<li class="has-line-data" data-line-start="153" data-line-end="154">Success: <code>{ &quot;message&quot;: &quot;Successfully created&quot; }</code></li>
<li class="has-line-data" data-line-start="154" data-line-end="156">Failure: <code>{ &quot;reason&quot;: &quot;Email already exists&quot; }</code></li>
</ul>
</li>
</ul>
</li>
<li class="has-line-data" data-line-start="156" data-line-end="162">
<p class="has-line-data" data-line-start="156" data-line-end="157"><strong>DELETE /user</strong></p>
<ul>
<li class="has-line-data" data-line-start="157" data-line-end="158">Delete an account.</li>
<li class="has-line-data" data-line-start="158" data-line-end="162"><strong>Data out:</strong>
<ul>
<li class="has-line-data" data-line-start="159" data-line-end="160">Success: <code>{ &quot;message&quot;: &quot;Successfully deleted&quot; }</code></li>
<li class="has-line-data" data-line-start="160" data-line-end="162">Failure: <code>{ &quot;reason&quot;: &quot;Wrong token&quot; }</code></li>
</ul>
</li>
</ul>
</li>
<li class="has-line-data" data-line-start="162" data-line-end="177">
<p class="has-line-data" data-line-start="162" data-line-end="163"><strong>PUT /user</strong></p>
<ul>
<li class="has-line-data" data-line-start="163" data-line-end="164">Update account information.</li>
<li class="has-line-data" data-line-start="164" data-line-end="173"><strong>Data in:</strong><pre><code class="has-line-data" data-line-start="166" data-line-end="173" class="language-json">{
  "<span class="hljs-attribute">change</span>": <span class="hljs-value">{
    "<span class="hljs-attribute">new-value</span>": <span class="hljs-value"><span class="hljs-string">"Schulz"</span></span>,
    "<span class="hljs-attribute">field</span>": <span class="hljs-value"><span class="hljs-string">"lastname"</span>
  </span>}
</span>}
</code></pre>
</li>
<li class="has-line-data" data-line-start="173" data-line-end="177"><strong>Data out:</strong>
<ul>
<li class="has-line-data" data-line-start="174" data-line-end="175">Success: <code>{ &quot;message&quot;: &quot;Field changed&quot; }</code></li>
<li class="has-line-data" data-line-start="175" data-line-end="177">Failure: <code>{ &quot;reason&quot;: &quot;Wrong token&quot; }</code></li>
</ul>
</li>
</ul>
</li>
</ul>
<h3 class="code-line" data-line-start=177 data-line-end=178 ><a id="4_Room_Endpoint_177"></a>4. <strong>Room Endpoint</strong></h3>
<p class="has-line-data" data-line-start="178" data-line-end="179">Manage rooms (create, delete, modify).</p>
<ul>
<li class="has-line-data" data-line-start="180" data-line-end="192">
<p class="has-line-data" data-line-start="180" data-line-end="181"><strong>GET /room</strong></p>
<ul>
<li class="has-line-data" data-line-start="181" data-line-end="182">Retrieve all rooms.</li>
<li class="has-line-data" data-line-start="182" data-line-end="192"><strong>Data out:</strong><pre><code class="has-line-data" data-line-start="184" data-line-end="191" class="language-json">{
  "<span class="hljs-attribute">rooms</span>": <span class="hljs-value">[
    { "<span class="hljs-attribute">room_id</span>": <span class="hljs-value"><span class="hljs-string">"room01"</span></span>, "<span class="hljs-attribute">name</span>": <span class="hljs-value"><span class="hljs-string">"Wohnzimmer"</span> </span>},
    ...
  ]
</span>}
</code></pre>
</li>
</ul>
</li>
<li class="has-line-data" data-line-start="192" data-line-end="202">
<p class="has-line-data" data-line-start="192" data-line-end="193"><strong>GET /room/{room_id}</strong></p>
<ul>
<li class="has-line-data" data-line-start="193" data-line-end="194">Retrieve a specific room.</li>
<li class="has-line-data" data-line-start="194" data-line-end="202"><strong>Data out:</strong><pre><code class="has-line-data" data-line-start="196" data-line-end="201" class="language-json">{
  "<span class="hljs-attribute">room_id</span>": <span class="hljs-value"><span class="hljs-string">"room01"</span></span>,
  "<span class="hljs-attribute">name</span>": <span class="hljs-value"><span class="hljs-string">"Wohnzimmer"</span>
</span>}
</code></pre>
</li>
</ul>
</li>
<li class="has-line-data" data-line-start="202" data-line-end="214">
<p class="has-line-data" data-line-start="202" data-line-end="203"><strong>POST /room</strong></p>
<ul>
<li class="has-line-data" data-line-start="203" data-line-end="204">Create a new room.</li>
<li class="has-line-data" data-line-start="204" data-line-end="210"><strong>Data in:</strong><pre><code class="has-line-data" data-line-start="206" data-line-end="210" class="language-json">{
  "<span class="hljs-attribute">name</span>": <span class="hljs-value"><span class="hljs-string">"Wohnzimmer"</span>
</span>}
</code></pre>
</li>
<li class="has-line-data" data-line-start="210" data-line-end="214"><strong>Data out:</strong>
<ul>
<li class="has-line-data" data-line-start="211" data-line-end="212">Success: <code>{ &quot;message&quot;: &quot;Successfully created&quot; }</code></li>
<li class="has-line-data" data-line-start="212" data-line-end="214">Failure: <code>{ &quot;reason&quot;: &quot;Name already exists&quot; }</code></li>
</ul>
</li>
</ul>
</li>
<li class="has-line-data" data-line-start="214" data-line-end="220">
<p class="has-line-data" data-line-start="214" data-line-end="215"><strong>DELETE /room/{room_id}</strong></p>
<ul>
<li class="has-line-data" data-line-start="215" data-line-end="216">Delete a room.</li>
<li class="has-line-data" data-line-start="216" data-line-end="220"><strong>Data out:</strong>
<ul>
<li class="has-line-data" data-line-start="217" data-line-end="218">Success: <code>{ &quot;message&quot;: &quot;Successfully deleted&quot; }</code></li>
<li class="has-line-data" data-line-start="218" data-line-end="220">Failure: <code>{ &quot;reason&quot;: &quot;Wrong token&quot; }</code></li>
</ul>
</li>
</ul>
</li>
<li class="has-line-data" data-line-start="220" data-line-end="235">
<p class="has-line-data" data-line-start="220" data-line-end="221"><strong>PUT /room/{room_id}</strong></p>
<ul>
<li class="has-line-data" data-line-start="221" data-line-end="222">Update room information.</li>
<li class="has-line-data" data-line-start="222" data-line-end="231"><strong>Data in:</strong><pre><code class="has-line-data" data-line-start="224" data-line-end="231" class="language-json">{
  "<span class="hljs-attribute">change</span>": <span class="hljs-value">{
    "<span class="hljs-attribute">new-value</span>": <span class="hljs-value"><span class="hljs-string">"Schlafzimmer"</span></span>,
    "<span class="hljs-attribute">field</span>": <span class="hljs-value"><span class="hljs-string">"name"</span>
  </span>}
</span>}
</code></pre>
</li>
<li class="has-line-data" data-line-start="231" data-line-end="235"><strong>Data out:</strong>
<ul>
<li class="has-line-data" data-line-start="232" data-line-end="233">Success: <code>{ &quot;message&quot;: &quot;Field changed&quot; }</code></li>
<li class="has-line-data" data-line-start="233" data-line-end="235">Failure: <code>{ &quot;reason&quot;: &quot;Wrong token&quot; }</code></li>
</ul>
</li>
</ul>
</li>
</ul>
<h3 class="code-line" data-line-start=235 data-line-end=236 ><a id="5_Routine_Endpoint_235"></a>5. <strong>Routine Endpoint</strong></h3>
<p class="has-line-data" data-line-start="236" data-line-end="237">Manage automated routines for smart devices.</p>
<ul>
<li class="has-line-data" data-line-start="238" data-line-end="260">
<p class="has-line-data" data-line-start="238" data-line-end="239"><strong>POST /routine</strong></p>
<ul>
<li class="has-line-data" data-line-start="239" data-line-end="240">Create a new routine.</li>
<li class="has-line-data" data-line-start="240" data-line-end="256"><strong>Data in:</strong><pre><code class="has-line-data" data-line-start="242" data-line-end="256" class="language-json">{
  "<span class="hljs-attribute">routine</span>": <span class="hljs-value">{
    "<span class="hljs-attribute">name</span>": <span class="hljs-value"><span class="hljs-string">"Guten Morgen"</span></span>,
    "<span class="hljs-attribute">actions</span>": <span class="hljs-value">[
      {"<span class="hljs-attribute">device_id</span>": <span class="hljs-value"><span class="hljs-string">"light01"</span></span>, "<span class="hljs-attribute">action</span>": <span class="hljs-value"><span class="hljs-string">"on"</span></span>},
      {"<span class="hljs-attribute">device_id</span>": <span class="hljs-value"><span class="hljs-string">"outlet1"</span></span>, "<span class="hljs-attribute">action</span>": <span class="hljs-value"><span class="hljs-string">"off"</span></span>}
    ]</span>,
    "<span class="hljs-attribute">trigger</span>": <span class="hljs-value">{
      "<span class="hljs-attribute">type</span>": <span class="hljs-value"><span class="hljs-string">"time"</span></span>,
      "<span class="hljs-attribute">value</span>": <span class="hljs-value"><span class="hljs-string">"07:00"</span>
    </span>}
  </span>}
</span>}
</code></pre>
</li>
<li class="has-line-data" data-line-start="256" data-line-end="260"><strong>Data out:</strong>
<ul>
<li class="has-line-data" data-line-start="257" data-line-end="258">Success: <code>{ &quot;message&quot;: &quot;Routine created&quot; }</code></li>
<li class="has-line-data" data-line-start="258" data-line-end="260">Failure: <code>{ &quot;reason&quot;: &quot;Invalid device_id&quot; }</code></li>
</ul>
</li>
</ul>
</li>
<li class="has-line-data" data-line-start="260" data-line-end="270">
<p class="has-line-data" data-line-start="260" data-line-end="261"><strong>GET /routine</strong></p>
<ul>
<li class="has-line-data" data-line-start="261" data-line-end="262">Retrieve all routines.</li>
<li class="has-line-data" data-line-start="262" data-line-end="270"><strong>Data out:</strong><pre><code class="has-line-data" data-line-start="264" data-line-end="269" class="language-json">[
  { "id": "routine01", "name": "Guten Morgen", "actions": [...], "trigger": {...} },
  ...
]
</code></pre>
</li>
</ul>
</li>
<li class="has-line-data" data-line-start="270" data-line-end="282">
<p class="has-line-data" data-line-start="270" data-line-end="271"><strong>GET /routine/{routine_id}</strong></p>
<ul>
<li class="has-line-data" data-line-start="271" data-line-end="272">Retrieve a single routine by ID.</li>
<li class="has-line-data" data-line-start="272" data-line-end="282"><strong>Data out:</strong><pre><code class="has-line-data" data-line-start="274" data-line-end="281" class="language-json">{
  "id": "routine01",
  "name": "Guten Morgen",
  "actions": [...],
  "trigger": {...}
}
</code></pre>
</li>
</ul>
</li>
<li class="has-line-data" data-line-start="282" data-line-end="297">
<p class="has-line-data" data-line-start="282" data-line-end="283"><strong>PUT /routine/{routine_id}</strong></p>
<ul>
<li class="has-line-data" data-line-start="283" data-line-end="284">Update a routine.</li>
<li class="has-line-data" data-line-start="284" data-line-end="293"><strong>Data in:</strong><pre><code class="has-line-data" data-line-start="286" data-line-end="293" class="language-json">{
  "<span class="hljs-attribute">change</span>": <span class="hljs-value">{
    "<span class="hljs-attribute">new-value</span>": <span class="hljs-value"><span class="hljs-string">"Guten Abend"</span></span>,
    "<span class="hljs-attribute">field</span>": <span class="hljs-value"><span class="hljs-string">"name"</span>
  </span>}
</span>}
</code></pre>
</li>
<li class="has-line-data" data-line-start="293" data-line-end="297"><strong>Data out:</strong>
<ul>
<li class="has-line-data" data-line-start="294" data-line-end="295">Success: <code>{ &quot;message&quot;: &quot;Routine updated&quot; }</code></li>
<li class="has-line-data" data-line-start="295" data-line-end="297">Failure: <code>{ &quot;reason&quot;: &quot;Invalid action&quot; }</code></li>
</ul>
</li>
</ul>
</li>
<li class="has-line-data" data-line-start="297" data-line-end="303">
<p class="has-line-data" data-line-start="297" data-line-end="298"><strong>DELETE /routine/{routine_id}</strong></p>
<ul>
<li class="has-line-data" data-line-start="298" data-line-end="299">Delete a routine.</li>
<li class="has-line-data" data-line-start="299" data-line-end="303"><strong>Data out:</strong>
<ul>
<li class="has-line-data" data-line-start="300" data-line-end="301">Success: <code>{ &quot;message&quot;: &quot;Routine deleted&quot; }</code></li>
<li class="has-line-data" data-line-start="301" data-line-end="303">Failure: <code>{ &quot;reason&quot;: &quot;Unauthorized&quot; }</code></li>
</ul>
</li>
</ul>
</li>
</ul>
<h2 class="code-line" data-line-start=303 data-line-end=304 ><a id="Authentication_303"></a>Authentication</h2>
<p class="has-line-data" data-line-start="305" data-line-end="306">All requests to the endpoints (except <code>/auth</code>) require the following header:</p>
<pre><code class="has-line-data" data-line-start="307" data-line-end="309">Authorization: 1234
</code></pre>

### Contributors
- <a href="https://github.com/DrSommer20">**Tim Sommer**</a> - Backend Developer
- <a href="https://github.com/paulib24">**Paula Bauer**</a> - Frontend Developer / Designer
- <a href="https://github.com/Chris-laws">**Chris David Kaufmann**</a> - Frontend Developer / Designer

<a href="https://github.com/DrSommer20/SmartHomeDashboard/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=DrSommer20/SmartHomeDashboard" />
</a>

### License
This project is licensed under the Apache 2.0 License.
