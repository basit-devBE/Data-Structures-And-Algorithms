public class DoublyLinkedListDemo {
    
    // Node class - the building block
    static class Node {
        int data;
        Node next;  // Points to the next node
        Node prev;  // Points to the previous node
        
        Node(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }
    
    static class DoublyLinkedList {
        Node head;
        
        // Let's create and connect nodes manually to see how it works
        public void demonstrateNodeConnections() {
            System.out.println("=== Creating First Node ===");
            Node firstNode = new Node(10);
            head = firstNode;
            System.out.println("First Node: data=" + firstNode.data);
            System.out.println("First Node next: " + firstNode.next);  // null
            System.out.println("First Node prev: " + firstNode.prev);  // null
            
            System.out.println("\n=== Creating Second Node ===");
            Node secondNode = new Node(20);
            System.out.println("Second Node: data=" + secondNode.data);
            
            System.out.println("\n=== Connecting First → Second ===");
            firstNode.next = secondNode;  // First points forward to second
            secondNode.prev = firstNode;  // Second points back to first
            
            System.out.println("Now firstNode.next points to: " + firstNode.next.data);
            System.out.println("Now secondNode.prev points to: " + secondNode.prev.data);
            
            System.out.println("\n=== Adding Third Node ===");
            Node thirdNode = new Node(30);
            secondNode.next = thirdNode;  // Second points forward to third
            thirdNode.prev = secondNode;  // Third points back to second
            
            System.out.println("\n=== Current List Structure ===");
            System.out.println("head → [10] ⟷ [20] ⟷ [30]");
            
            System.out.println("\n=== Navigating Forward (using next) ===");
            Node current = head;
            while (current != null) {
                System.out.print(current.data + " → ");
                current = current.next;
            }
            System.out.println("null");
            
            System.out.println("\n=== Navigating Backward (using prev) ===");
            // First, go to the last node
            current = head;
            while (current.next != null) {
                current = current.next;
            }
            // Now traverse backward
            while (current != null) {
                System.out.print(current.data + " → ");
                current = current.prev;
            }
            System.out.println("null");
            
            System.out.println("\n=== Accessing Middle Node from First ===");
            Node middle = head.next;
            System.out.println("Middle node data: " + middle.data);
            System.out.println("Middle's previous: " + middle.prev.data);
            System.out.println("Middle's next: " + middle.next.data);
            
            System.out.println("\n=== Accessing Middle Node from Last ===");
            Node last = thirdNode;
            Node middleFromLast = last.prev;
            System.out.println("Middle node data: " + middleFromLast.data);
            System.out.println("Can go forward: " + middleFromLast.next.data);
            System.out.println("Can go backward: " + middleFromLast.prev.data);
        }
        
        // Insert a new node between two existing nodes
        public void insertBetween(Node prevNode, int newData) {
            if (prevNode == null) {
                System.out.println("Previous node cannot be null");
                return;
            }
            
            System.out.println("\n=== Inserting " + newData + " after " + prevNode.data + " ===");
            
            Node newNode = new Node(newData);
            Node nextNode = prevNode.next;
            
            // Step 1: Connect new node to its neighbors
            newNode.next = nextNode;
            newNode.prev = prevNode;
            
            // Step 2: Update previous node's next pointer
            prevNode.next = newNode;
            
            // Step 3: Update next node's prev pointer (if it exists)
            if (nextNode != null) {
                nextNode.prev = newNode;
            }
            
            System.out.println("Connected: [" + prevNode.data + "] ⟷ [" + newData + "] ⟷ [" + (nextNode != null ? nextNode.data : "null") + "]");
        }
        
        // Delete a middle node
        public void deleteNode(Node nodeToDelete) {
            if (nodeToDelete == null) return;
            
            System.out.println("\n=== Deleting node with data: " + nodeToDelete.data + " ===");
            
            Node prevNode = nodeToDelete.prev;
            Node nextNode = nodeToDelete.next;
            
            // Connect previous and next nodes to each other
            if (prevNode != null) {
                prevNode.next = nextNode;
            } else {
                // Deleting head node
                head = nextNode;
            }
            
            if (nextNode != null) {
                nextNode.prev = prevNode;
            }
            
            System.out.println("Reconnected: [" + (prevNode != null ? prevNode.data : "null") + "] ⟷ [" + (nextNode != null ? nextNode.data : "null") + "]");
        }
        
        public void printList() {
            System.out.print("\nCurrent list: ");
            Node current = head;
            while (current != null) {
                System.out.print(current.data + " ");
                current = current.next;
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();
        
        // Demonstrate basic node connections
        list.demonstrateNodeConnections();
        
        // Demonstrate insertion
        list.insertBetween(list.head.next, 25);  // Insert 25 between 20 and 30
        list.printList();
        
        // Demonstrate deletion
        list.deleteNode(list.head.next);  // Delete the second node
        list.printList();
        
        System.out.println("\n=== KEY CONCEPT ===");
        System.out.println("Each node has TWO pointers:");
        System.out.println("  - 'next' lets you move forward");
        System.out.println("  - 'prev' lets you move backward");
        System.out.println("This bidirectional connection is what makes it 'doubly' linked!");
    }
}
