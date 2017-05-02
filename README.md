[![source](https://img.shields.io/badge/source-main-brightgreen.svg)][main]
[![source](https://img.shields.io/badge/source-test-yellow.svg)][test]
[![implementation](https://img.shields.io/badge/implementation-java-blue.svg)][java]

[main]: https://github.com/BBK-PiJ-2016-52/SDP/tree/master/coursework/cw-one/src/main/scala/sml
[test]: https://github.com/BBK-PiJ-2016-52/SDP/tree/master/coursework/cw-one/src/test/scala/sml
[java]: http://docs.oracle.com/javase/8/docs/api/

## Coursework Assignment 4 - Philip Hammond and the Temple of Gloom! :bookmark_tabs: :mag: :postbox:   

#### Overview

I'll help the explorer and professor of archeology Philip Hammond claim the Orb of Lots, which is located in the Temple of Gloom. You will help him explore
an unknown cavern under the temple, claim the orb, and escape before the entrance
collapses. There will be great rewards for those who help Philip fill his pockets with gold
on the way out. There are two major phases to this assignment:  
• the *exploration phase*  
• the *escape phase*  
each of which involves writing a separate method in Java. 

#### The Exploration Phase
On the way to the Orb (see Figure 1), the layout of the cavern is unknown. You know
only the status of the tile on which you are standing and those immediately around you
and perhaps those that you remember. Your goal is to make it to the Orb in as few steps
as possible. This is not a blind search, as you will know the distance to the Orb. This 
is equal to the number of tiles on the shortest path to the Orb, if there weren’t walls or
obstacles in the way.


![](http://i.imgur.com/4IOnOCV.png)
###### Figure 1. Searching for the Orb during the exploration phase


You will develop the solution to this phase in the method explore() in the class
Explorer within package student. There is no time limit on the number of steps you
can take, but you will receive a higher score for finding the Orb in fewer steps. To pick
up the Orb, simply return from this method once you have arrived on its tile. Returning
when Philip is not on the Orb tile will cause an exception to be thrown, halting the game.
When writing method explore(), you are given an ExplorationState object to
learn about the environment around you. Every time you make a move, this object
automatically changes to reflect the new location of the explorer. This object includes
the following methods:
is equal to the number of tiles on the shortest path to the Orb, if there weren’t walls or
obstacles in the way.
You will develop the solution to this phase in the method explore() in the class
Explorer within package student. There is no time limit on the number of steps you
can take, but you will receive a higher score for finding the Orb in fewer steps. To pick
up the Orb, simply return from this method once you have arrived on its tile. Returning
when Philip is not on the Orb tile will cause an exception to be thrown, halting the game.
When writing method explore(), you are given an ExplorationState object to
learn about the environment around you. Every time you make a move, this object
automatically changes to reflect the new location of the explorer. This object includes
the following methods:
long getCurrentLocation(): return a unique identifier for the tile the explorer is currently
on.
int getDistanceToTarget(): return the distance from the explorers current location
to the Orb.
Collection<NodeStatus> getNeighbours(): return information about the tiles to which
the explorer can move from their current location.
void moveTo(long id): move the explorer to the tile with ID id. This fails if that tile
is not adjacent to the current location.
Note that function getNeighbours() returns a collection of NodeStatus objects. This
is simply an object that contains, for each neighbour, the ID corresponding to that
neighbour, as well as that neighbours distance from the Orb. You can examine the
documentation for this class for more information on how to use NodeStatus objects. A
suggested first implementation that will always find the Orb, but likely as not wont receive
a large bonus score, is a depth-first search. More advanced solutions might incorporate
the distance of tiles from the Orb.




#### Repository Structure
```
├───cw-temple
│   ├───.gradle
│   │   ├───3.4.1
│   │   │   └───taskHistory
│   │   └───buildOutputCleanup
│   ├───.idea
│   │   ├───inspectionProfiles
│   │   ├───libraries
│   │   └───modules
│   ├───build
│   │   ├───classes
│   │   │   └───main
│   │   ├───reports
│   │   │   ├───checkstyle
│   │   │   ├───findbugs
│   │   │   ├───jdepend
│   │   │   └───pmd
│   │   └───resources
│   ├───config
│   │   ├───checkstyle
│   │   └───xsl
│   ├───gradle
│   │   └───wrapper
│   ├───out
│   │   └───production
│   │       └───cw-temple
│   │           └───images
│   ├───res
│   └───src
│       ├───main
│       │   ├───java
│       │   │   ├───game
│       │   │   ├───gui
│       │   │   ├───main
│       │   │   ├───searchexample
│       │   │   └───student
│       │   └───resources
│       │       └───images
│       └───test
│           └───java
```
#### Developed by
 
|                                                                                                 | username                                                       | about                               |
--------------------------------------------------------------------------------------------------|----------------------------------------------------------------|---------------------------------------------------|
 <img src="https://avatars0.githubusercontent.com/u/22904851?v=3&u=cfb4a9acace450d6628c1c80ce6e46c985e178d2&s=400"      height="50px" title="Eric Rodriguez"/>        | [`@BBK-PiJ-2016-52`](https://github.com/BBK-PiJ-2016-52)           | Eric Rodriguez - B.Eng. Mechatronics|
