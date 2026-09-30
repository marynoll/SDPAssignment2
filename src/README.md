Assignment 2: Factory Method & Abstract Factory

Part A (Initial Flaws): Direct object creation caused tight coupling, 
violated the Open/Closed Principle (OCP), and allowed incompatible cloud services to be mixed.

Part B & C (Design Patterns):
Abstract Factory: CloudFactory ensures only compatible product families (Compute, Database, Storage) 
are created together.
Factory Method: The BackupJob (Creator) defines backup logic but delegates createStorage() 
to subclasses to determine the specific storage type.

Part D (Compatibility Rule): Generics (CloudFactory<T>) enforce compile-time safety.
Mixing products from different families (e.g., AWS Storage with GCP Compute) causes a compilation error,
making invalid combinations structurally impossible.

Part E (Runtime Selection): AppConfig dynamically selects the ecosystem factory at startup based on priority: 
CLI arguments > Environment Variables > Properties File > Default.

Part F (Business Scenario): Products collaborate seamlessly in the DeploymentService.
A Compute instance mounts the Storage, and the Database runs scheduled backups to that Storage.

Part G (Adding a 4th Family): Added the OnPrem (On-Premises) family.
This required adding new specific classes and one routing line in AppConfig, with zero modifications 
to existing client or business logic, successfully demonstrating OCP.

Part H (UML): The diagram explicitly separates and highlights the Abstract Factory and Factory Method 
boundaries.

Part I (Testing): A robust JUnit 5 suite (15+ tests) verifies family creation, compile-time compatibility,
runtime configuration priorities, and business logic.

Part J (Git History): Iterative commits demonstrate the transition from a tightly coupled initial state
to a fully extensible, pattern-based architecture.