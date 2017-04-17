# 15-Puzzle

A sliding puzzle that consists of a frame of numbered square tiles 
in a random order with one tile missing. 

Game is represented by a 4x4 tile board where 15 numbered tiles are initially 
placed in a random order and where the 16th tile is missing. 
A tile can be moved to an adjacent empty spot.

To succeed in the game you need to order tiles from 1 to 15, where tile
number 1 is at the top-left corner and the empty one is at the bottom-right.

## Running app

Pre-requisites:

- JDK8

```
$ ./gradlew build
$ ./gradlew bootRun
```

After startup, the following are accessible:

API docs: http://localhost:8080/swagger-ui.html
API: http://localhost:8080/api

## Design notes

- Game is fully controllable via truly RESTful API, using HAL links from 
API root `/api`
- Goal was simplicity without sacrificing foundation principles on class/package/API design