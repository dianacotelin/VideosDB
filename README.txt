Cotelin Diana
November 2021

~~~~~~~~~~~~~~~~~~~~~~~~~~~~ VideosDB~~~~~~~~~~~~~~~~~~~~~~~~~
* Video Platform & Database
    - the program is an implementation of a video platform which can store videos of different types
    actors that played in those, users and do operations on them;

__Classes__

- Database -> implemented with lazy instantiation a Singleton that is going to be used
            in all the platform and is going to be flushed after the use of it (in our case on
            a test)
            -> contains lists of Actors, Movies, Shows, Videos (abstract class), Users, a Commander
            control panel and genreMap;

- User -> abstract class that is going to be inherited by two types of users to use same methods;
       -> STANDARD -> can do only 2 actions;
       -> PREMIUM -> can do the default action+ 3 more actions;

- Actor

- Video -> abstract class that is going to be inherited by two types of videos Movies and TV Shows
           to use the same methods on children;
        -> Movie -> inherits Video;
        -> Show -> inherits Video;

- Commander -> a commander class where the actions will be extracted from an input JSON parsed List
               and then be executed in function of different types of actions;

- Action -> abstract class that is going to be inherited by three types of command such as Query
           Basic Command and Recommendation
         -> Basic Command
         -> Recommendation
         -> Query (on 3 differents objects)



