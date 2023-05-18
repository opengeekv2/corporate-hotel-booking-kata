CIP

Explore the ways kotlin provides to have an application that can be compiled and tested in different parts and also work
as a whole. Also keep going with the kata to find the problems we may have to overcome by working that way.

This iniciative is valuable to Scentmate so the team can get experience in working in a modular setting.

A modular setting will improve our productivity by reducing testing time (as only changed modules might get tested) and
also paves the way to the extraction of the model as a service.

To explore it, we're doing a kata known for having different domains that have to cooperate to solve the kata known as
the Hotels Booking kata.

A Kata is suggested as a practice so we deal first with the challenges related with the approach and then skip to the
current system and deal with the complexity of applying it to our context but many things will be learnt that will allow
to focus on outr particularities. Let's try not to learn everything at once.

To see the problems we might find when testing we're using BDD and TDD for the kata.

Company, Hotel and Booking Policy modules must compile and test independently but also be able to build and compile together. 

We have got to a point we needed some fixtures of a module in another module and find a way to solve that being the
Java Test Fixtures Gradle Plugin that exposes the compiled fixtures to other modules.

We haven't yet got to a point where implementation code has to use other modules code so still some progress has to be
made in the kata to get a that point.

The kata is written with minimal infra as it would only distract us from the primary goal, however it's open to add some
infra. It's architected in a way it allows to defer infrastructure decisions by testing against in memory repositories.
