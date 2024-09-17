# Simple Pub-Sub system

Implementation of a simple Pub-Sub system with a non-blocking publish() function.

Uses a worker thread that busy waits for events in the request queue and 
publishes them when an event is added to the queue. 