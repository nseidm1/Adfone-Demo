Adfone Demo App

An Instagram demo application has been made to load a users personal stream. The app uses the token based API, and retrieves an accessToken
using the relevant Instagram access page in a WebView. Redirect overrides are utilized to intercept the user, and token. When the desired 
data is available the InstagramFragment is loaded. Retrofit and RX are wrapped by a delegate to load the users stream data. ViewModels 
are used to bind the retrieved models to a RecyclerView, which is backed by a MultiTypeDataBoundAdapter. The elegenace of the mentioned 
components are highlighted by the simplicity of the InstagramFragment which is only 76 lines of code. A simple test is included using 
AndroidX FragmentScenario, Square's Mock Web Server, and Mockinizer to execute the InstagramFragment. While not overwhelming in test 
coverage, the combination of the AndroidX ActivityScenario, FragmentScenario and traditional unit tests, makes substantial coverage quite 
achievable. Dagger is used for injection purposes, which proves useful in varying the networking implementation during the test execution.
