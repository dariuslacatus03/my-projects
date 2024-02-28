# Toy Language Interpreter

This Java program serves as a toy language interpreter built on the Model-View-Controller (MVC) architecture. The interpreter supports a variety of statements, types, values, and expressions, all implemented through interface classes for modularity.

## Key Features:

- **Statements Implementation:**
  - Assignment, Await, Compound, Conditional Assignment, Countdown, Fork, If, Latch, Nop, Print, Variable Declaration, While, File Statements (Open, Read, Close), Heap Statements (New, Write To Heap).

- **Types Implementation:**
  - Bool, Int, Reference, String, all encapsulated using an interface class.

- **Values Implementation:**
  - Bool, Int, Reference, String, providing flexibility and uniformity through an interface class.

- **Expressions Implementation:**
  - Arithmetic, Logic, Read Heap, Relational, Value, Variable, all implemented via an interface class.

- **Program State Tracking:**
  - The interpreter manages one or more program states, employing an execution stack, symbol table, output list, file table, heap table, and latch table. These data structures are implemented as abstract data types: MyDictionary, MyHeapTable, MyLatchTable, MyList, MyStack.

- **User Interfaces:**
  - The program offers both console and graphical user interfaces, enabling users to choose a predefined program state and execute it step by step, observing the changes in each of the underlying tables.

This interpreter provides a comprehensive environment for experimenting with a toy language, offering insights into the internal workings of a program state. Explore and interact with different statements, types, and expressions, making it a good learning tool for language interpretation concepts.
