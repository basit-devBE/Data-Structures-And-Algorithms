
import java.util.LinkedList;

public class Linked {
    LinkedList<String> list = new LinkedList<>();

    String firstElement;
    String alsoFirstElement;
    String anotherFirst;

    public Linked() {
        list.add("A");
        list.add("B");
        list.addFirst("C");
        list.add(0, "Document");
     // Adds "C" at the beginning
        list.removeLast();  // Removes the last element
        firstElement = list.get(0); // Accesses the first element
        alsoFirstElement = list.peekFirst(); // Accesses the first element without removing it
        anotherFirst = list.getFirst();
        list.set(0, alsoFirstElement);
        list.remove(anotherFirst);

        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }
    }

    public static void main(String[] args) {
        Linked l = new Linked();
        System.out.println(l.list);
    }
}
//LinkedLists are a dynamic data structure where each element (node) contains a value and a reference (link) to the next node in the sequence.
// Unlike arrays, linked lists do not require contiguous memory allocation, allowing for efficient insertions and deletions.
// There are several types of linked lists, including singly linked lists (where each node points to the next node),
// doubly linked lists (where nodes point to both the next and previous nodes), and circular linked lists (where the last node points back to the first node).
// Linked lists are commonly used in scenarios where dynamic memory allocation is needed, such as implementing stacks, queues, and other abstract data types.
//Each Node consists of two parts: data and a reference (or pointer) to the next node in the sequence.
// The first node in a linked list is called the head, and the last node points to null, indicating the end of the list.
// Basic operations on linked lists include insertion (adding a new node), deletion (removing a node), and traversal (visiting each node in the list).
// Linked lists can be more memory-intensive than arrays due to the storage of pointers, but they offer greater flexibility for dynamic data management.
//It implements the List and Deque interfaces, providing methods for adding, removing, and accessing elements.
// Java's LinkedList class is part of the java.util package and provides a doubly linked list implementation.
// It allows for efficient insertions and deletions from both ends of the list, making it suitable for queue and stack implementations.
// Example usage of Java's LinkedList:
// import java.util.LinkedList;
//
// LinkedList<String> list = new LinkedList<>();
// list.add("A");
// list.add("B");
// list.addFirst("C"); // Adds "C" at the beginning
// list.removeLast();  // Removes the last element
// String firstElement = list.get(0); // Accesses the first element
// System.out.println(list); // Output: [C, A]

