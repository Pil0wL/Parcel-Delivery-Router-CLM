# Parcel Delivery Router

A Java program which simulates a real-world logistics system for parcel routing, sorting, and delivery operations.

## Overview / Description

The **Parcel Delivery Router simulation** is a Java-based program that models how parcels move through a delivery network using data structures and algorithms. It reflects real-world logistics operations, where packages are stored, sorted, queued for pickup, prioritized based on fragility, routed, and logged upon delivery.

The purpose of this simulation is to demonstrate applied understanding of **arrays, sorting algorithms, stacks, queues, heaps, linked lists, and binary search trees** within an operational workflow. The system solves problems related to parcel organization, priority handling, route planning, and efficient lookup of parcel data.

## Features

* **Parcel Roster Management** (Array-based)
* **Multiple Sorting Algorithms**
  * Bubble Sort (names)
  * Insertion Sort (ZIP codes)
  * Selection Sort (weight)
* **Queue System:** FIFO pickup handling
* **Priority Queue / Heap:** Fragile-first parcel prioritization
* **Stack-based Undo / Redo** for route editing
* **Singly Linked List:** Delivery drop-off log
* **Doubly Linked List:** Forward/back route navigation
* **Binary Search Tree:** Insert, search, delete, traversal
* **Heap Top-K Load Planner**
* **Interactive Menu System**
* **Error Handling:** Overflow/underflow warnings, null checking, invalid input handling

## System Requirements

* **JDK 17 or higher**
* IDE (any):
  * IntelliJ IDEA
  * Eclipse
  * NetBeans
  * Visual Studio Code (Java Extensions)
* No external libraries required (pure Java implementation)

## How to Install / Run the Program

### Installation


Visit:**https://github.com/Pil0wL/Parcel-Delivery-Router-CLM**

### Running the Program (Terminal)

```sh
cd src
javac app/ParcelDeliveryRouterApp.java
java app.ParcelDeliveryRouterApp
```

### Running in an IDE


1. Open the project folder
2. Locate `ParcelDeliveryRouterApp.java`
3. Click **Run**

## VI. Program Structure

### **Package: app**

* **ParcelDeliveryRouterApp.java**
  * Main entry point
  * Controls the interactive menu and orchestrates all components

### **Package: ds (Data Structures)**

#### **Binary Search Trees**

* **BSTRoute.java** – Organizes parcels by Route ID or delivery sequence
* **BSTZIP.java** – Efficient lookup and sorting based on ZIP code

#### **Queue**

* **Queue.java** – Basic array-based FIFO queue

#### **Sorting**

* **Sorter.java** – Contains Bubble Sort, Insertion Sort, Selection Sort

#### **Stack**

* **StackX.java** – Stack for undoing/redoing route edits

#### **Priority Queue / Heap**

* **PriorityQueue.java** – Fragile/perishable-first dispatching

#### **Linked Lists**

* **SingleLinkedList.java** – For delivery logs
* **DoublyLinkedList.java** – Bidirectional route navigation

## Package: model

* **Parcel.java** – Core parcel structure (id, name, zip, weight, fragility)
* **RouteEditAction.java** – Route edit snapshot for undo/redo
* **VEHICP.java** – Vehicle/transit model (pickup/delivery state)

## Package: util

* **ParcelArrayHelper.java** – Sorting helpers, array utilities
* **Platform.java** – System-wide utilities and formatting

## Package: ui

* **UIBase.java** – Input handling, console utilities
* **UIIndex.java** – Main menu / navigation hub
* **UIParcel.java** – Parcel creation and editing interface
* **UIPerishableQueue.java** – Interactions with fragile priority queue
* **UIPickupTransit.java** – Pickup queue + truck transit handler
* **UIRouteOperator.java** – Route editing + undo/redo operations
* **UITruckControls.java** – Dispatch, routing flow, and load display

## VII. Usage Guide

When the program launches, users are greeted with an interactive console menu. Each menu module corresponds to a different parcel operation:

* **Parcel Management** – Add/view/modify parcels
* **Sorting Tools** – Apply Bubble, Insertion, Selection sort
* **Pickup Queue** – Standard FIFO queue
* **Priority Queue** – Fragile/perishable items processed first
* **Route Editing** – Stack-based undo/redo
* **Truck Operations** – Simulated movement from pickup → transit → delivery

Inputs use simple text prompts; results appear immediately in the console.

## VIII. Simulation Workflow


1. Users begin by entering parcels into the system.
2. Parcels may be sorted by **name**, **ZIP**, or **weight**.
3. Regular items proceed to the **FIFO pickup queue**, while fragile ones go to the **priority queue**.
4. Route changes can be edited and undone via the **stack** module.
5. The truck simulation processes parcels through:
   * Pickup
   * Transit
   * Drop-off
6. All deliveries are logged via the linked list structures.

## IX. Limitations

* Console-based (no GUI)
* No file persistence
* Priority queue supports only one rule (fragile-first)
* Not designed for large dataset efficiency

## X. Future Improvements

* Develop GUI (JavaFX or Web UI)
* Add database storage
* Multiple priority rules (time-sensitive, geographic, etc.)
* GPS-like routing features
* Automated testing

## XI. Authors / Acknowledgements

* **Emperador, Wrenzo Rafael A.**, 2nd Year BSCOME
* **Libetario, Cadmon Jas A.**, 2nd Year BSCOME
* Final Project for **Data Structure and Algorithm**
* Instructor: **Engr. Buena, John Pio Anthony A., CCpe**


