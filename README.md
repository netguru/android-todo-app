<!-- 
    Couple of points about editing:
    
    1. Keep it SIMPLE.
    2. Refer to reference docs and other external sources when possible.
    3. Remember that the file must be useful for new / external developers, and stand as a documentation basis on its own.
    4. Try to make it as informative as possible.
    5. Do not put data that can be easily found in code.
    6. Include this file on ALL branches.
-->

<!-- Put your project's name -->
# Android todo app - Room sample project

## Synopsis
<!-- Describe the project in few sentences -->
Android todo app is a small sample project developed to showcase [Room library](https://developer.android.com/topic/libraries/architecture/room.html).
It's part of article on our blog :
<!-- Todo UPDATE THIS PART -->
- [Part 1 - ToDo](https://www.netguru.co/blog)
- [Part 2 - ToDo](https://www.netguru.co/blog)

## Development

### Architecture
<!-- Describe the main architectural pattern used in the project, optionally put a flowchart -->
App use MVP patttern in presentation layer and variation of Clean Architecture in domain and data layer.

### Libraries
- Room
- RxJava2
- Stetho - current db state can be checked through `chrome://inspect`