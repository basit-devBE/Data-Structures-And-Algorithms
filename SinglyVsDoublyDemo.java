public class SinglyVsDoublyDemo {
    
    // SINGLY LINKED LIST - Only 'next' pointer
    static class SinglyNode {
        int data;
        SinglyNode next;  // Can only go forward →
        
        SinglyNode(int data) {
            this.data = data;
        }
    }
    
    // DOUBLY LINKED LIST - Both 'next' and 'prev' pointers
    static class DoublyNode {
        int data;
        DoublyNode next;  // Can go forward →
        DoublyNode prev;  // Can go backward ←
        
        DoublyNode(int data) {
            this.data = data;
        }
    }
    
    // ========== SINGLY LINKED LIST OPERATIONS ==========
    static class SinglyLinkedList {
        SinglyNode head;
        
        void add(int data) {
            if (head == null) {
                head = new SinglyNode(data);
                return;
            }
            SinglyNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new SinglyNode(data);
        }
        
        // PROBLEM: To delete a node, we need to find the PREVIOUS node
        void deleteNode(int value) {
            if (head == null) return;
            
            // Special case: deleting head
            if (head.data == value) {
                head = head.next;
                return;
            }
            
            // We MUST traverse to find the node BEFORE the one we want to delete
            SinglyNode current = head;
            while (current.next != null && current.next.data != value) {
                current = current.next;
            }
            
            if (current.next != null) {
                current.next = current.next.next;  // Skip over the deleted node
            }
        }
        
        // PROBLEM: Can't traverse backward - must restart from head
        void printReverse() {
            System.out.println("Printing in reverse (using recursion - inefficient):");
            printReverseHelper(head);
            System.out.println();
        }
        
        void printReverseHelper(SinglyNode node) {
            if (node == null) return;
            printReverseHelper(node.next);
            System.out.print(node.data + " ");
        }
        
        void print() {
            SinglyNode current = head;
            while (current != null) {
                System.out.print(current.data + " → ");
                current = current.next;
            }
            System.out.println("null");
        }
    }
    
    // ========== DOUBLY LINKED LIST OPERATIONS ==========
    static class DoublyLinkedList {
        DoublyNode head;
        DoublyNode tail;  // We can maintain a tail pointer for efficiency
        
        void add(int data) {
            DoublyNode newNode = new DoublyNode(data);
            if (head == null) {
                head = tail = newNode;
                return;
            }
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        
        // ADVANTAGE: Can delete directly without finding previous node
        void deleteNode(DoublyNode node) {
            if (node == null) return;
            
            // Update previous node's next pointer
            if (node.prev != null) {
                node.prev.next = node.next;
            } else {
                head = node.next;  // Deleting head
            }
            
            // Update next node's prev pointer
            if (node.next != null) {
                node.next.prev = node.prev;
            } else {
                tail = node.prev;  // Deleting tail
            }
            // No traversal needed!
        }
        
        // ADVANTAGE: Can traverse backward efficiently
        void printReverse() {
            System.out.println("Printing in reverse (direct traversal):");
            DoublyNode current = tail;
            while (current != null) {
                System.out.print(current.data + " → ");
                current = current.prev;
            }
            System.out.println("null");
        }
        
        void print() {
            DoublyNode current = head;
            while (current != null) {
                System.out.print(current.data + " → ");
                current = current.next;
            }
            System.out.println("null");
        }
    }
    
    public static void main(String[] args) {
        System.out.println("======= SINGLY LINKED LIST =======");
        SinglyLinkedList singly = new SinglyLinkedList();
        singly.add(10);
        singly.add(20);
        singly.add(30);
        singly.add(40);
        
        System.out.print("Forward: ");
        singly.print();
        
        singly.printReverse();  // Requires recursion - inefficient
        
        System.out.println("\nDeleting 20 (needs to find previous node)...");
        singly.deleteNode(20);
        singly.print();
        
        System.out.println("\n\n======= DOUBLY LINKED LIST =======");
        DoublyLinkedList doubly = new DoublyLinkedList();
        doubly.add(10);
        doubly.add(20);
        doubly.add(30);
        doubly.add(40);
        
        System.out.print("Forward: ");
        doubly.print();
        
        doubly.printReverse();  // Direct backward traversal - efficient
        
        System.out.println("\nDeleting node with value 20 (direct deletion)...");
        // Find the node first
        DoublyNode nodeToDelete = doubly.head.next;  // The second node (20)
        doubly.deleteNode(nodeToDelete);
        doubly.print();
        
        System.out.println("\n\n======= KEY DIFFERENCES =======");
        System.out.println("1. MEMORY:");
        System.out.println("   Singly: 1 pointer per node (next)");
        System.out.println("   Doubly: 2 pointers per node (next + prev) - MORE MEMORY");
        
        System.out.println("\n2. DELETION:");
        System.out.println("   Singly: O(n) - must find previous node");
        System.out.println("   Doubly: O(1) - can delete directly if you have the node");
        
        System.out.println("\n3. BACKWARD TRAVERSAL:");
        System.out.println("   Singly: Requires recursion or rebuilding - O(n) space");
        System.out.println("   Doubly: Direct traversal from tail - O(1) space");
        
        System.out.println("\n4. INSERT BEFORE A NODE:");
        System.out.println("   Singly: Need to find previous node - O(n)");
        System.out.println("   Doubly: Direct access via prev pointer - O(1)");
        
        System.out.println("\n======= WHEN TO USE EACH =======");
        System.out.println("Use SINGLY when:");
        System.out.println("  - Only forward traversal needed");
        System.out.println("  - Memory is constrained");
        System.out.println("  - Simple stack implementation");
        
        System.out.println("\nUse DOUBLY when:");
        System.out.println("  - Need backward traversal (e.g., browser back/forward)");
        System.out.println("  - Frequent deletions from middle");
        System.out.println("  - Implementing deque (double-ended queue)");
        System.out.println("  - LRU cache implementation");
        System.out.println("  - Undo/Redo functionality");
    }
}
