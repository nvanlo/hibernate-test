# Context
Test hibernate exceptions when persisting an entity with an already existing id in 2 scenarios
- The entity is not yet loaded in the Entity Manager
- Entity with the same id as the one we want to create is loaded and present in the Entity Manager cache