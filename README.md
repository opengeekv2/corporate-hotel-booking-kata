CIP

Explore the ways kotlin provides to have an application that can be compiled and testes in different parts and also work
as a whole. Also keep going with the kata to find the problems we may have to overcome by working that way.

To explore it, we're doing a kata known for having different domains that have to cooperate to solve the kata known as
the Hotels Booking kata.

To see the problems we might find when testing we're using BDD and TDD for the kata.

Company, Hotel and Booking Policy modules compile and test independently.

We have got to a point we needed some fixtures of a module in another module and find a way to solve that being the
Java Test Fixtures Gradle Plugin that exposes the compiled fixtures to other modules.

We haven't yet got to a point where implementation code has to use other modules code so still some progress has to be
made in the kata to get a that point.

The kata is written with minimal infra as it would only distract us from the primary goal, however it's open to add some
infra. It's architected in a way it allows to defer infrastructure decisions by testing against in memory repositories.
