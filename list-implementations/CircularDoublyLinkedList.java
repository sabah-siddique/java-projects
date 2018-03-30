package project2;

/**
 * This class represents a circular doubly linked list.
 * It stores two reference variables to access the list, head and tail, and a size variable to keep 
 * track of how many nodes are in the list.
 * 
 * @author Sabah Siddique
 * 
 */
public class CircularDoublyLinkedList<E> implements Cloneable {
    /**
     * This class represents a node.
     * It stores the reference to the element stored at this Node, the reference to the previous node (as
     * specified by the user), and the reference to the next node (as specified by the user).
     */
    private static class Node<E> {
        private E element;
        private Node<E> prev;
        private Node<E> next;
        /**
         * Constructs a new Node with specified element of type E, previous linked node, and next linked node.
         * @param element element of type E to be stored at this node
         * @param prev previous node
         * @param next next node
         */
        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

        /**
         * Returns the element stored at this node
         * @return the element stored at this node
         */
        public E getElement() {
            return element;
        }
        /**
         * Returns the previous linked node of this node
         * @return the previous linked node of this node
         */
        public Node<E> getPrev() {
            return prev;
        }
        /**
         * Returns the next linked node of this node
         * @return the next linked node of this node
         */
        public Node<E> getNext() {
            return next;
        }
        /**
         * Sets the previous linked node of this node
         * @param prev previous node to be linked
         */
        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }
        /**
         * Sets the next linked node of this node
         * @param next next node to be linked
         */
        public void setNext(Node<E> next) {
            this.next = next;
        }
    }
    /* End of Node class */

    /* CircularDoublyLinkedList class */
    private Node<E> head;
    private Node<E> tail;
    private int size;

    /**
     * Constructs a new CircularDoublyLinkedList object ?????????????
     */
    public CircularDoublyLinkedList() {
        // Temporary dummy head and tail nodes linked circularly
        head = new Node<>(null, null, null);
        tail = new Node<>(null, head, tail);
        head.setNext(tail);
        head.setPrev(tail);
        size = 0;
    }

    /**
     * Returns the size of the list
     * @return size of the list
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if the list is empty
     * @return true if the size of the list is zero
     *         false if otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the element stored at the first node of the list
     * @return the element stored at the first node of the list
     */
    public E first() {
        if (isEmpty())
            return null;
        return head.getElement();
    }

    /**
     * Returns the element stored at the last node of the list
     * @return the element stored at the last node of the list
     */
    public E last() {
        if (isEmpty())
            return null;
        return tail.getElement();
    }

    /**
     * Adds the specified element to the list at the first position
     * @param e the element to be added to the list at the first position
     */
    public void addFirst(E e) {
        if (isEmpty())
            addFirstNodeOfList(e);
        else
            addBetween(e, "first");
    }

    /**
     * Adds the specified element to the list at the last position
     * @param e the element to be added to the list at the last position
     */
    public void addLast(E e) {
        if (isEmpty())
            addFirstNodeOfList(e);
        else
            addBetween(e, "last");
    }
    
    /**
     * Create the first (non-dummy) node of the list and adds it
     * @param e the element to be added to the node created
     */
    private void addFirstNodeOfList(E e) {
        Node<E> newest = new Node<>(e, null, null);
        head = newest;
        tail = newest;
        head.setNext(tail);
        tail.setPrev(head);
    }

    /**
     * Creates a new node and adds it to the list
     * @param e the element to be added to the node created
     * @param position a string indicating which position, either first or last, the node should be added to
     */
    private void addBetween(E e, String position) {
        // Create and link a new node
        Node<E> newest = new Node<>(e, tail, head);
        head.setPrev(newest);
        tail.setNext(newest);
        // Set the new head or new tail appropriately
        if (position == "first")
            head = newest;
        else if (position == "last")
            tail = newest;
        // Update size
        size++;
    }

    /**
     * Removes the first node of the list
     * @return the element that was being stored at the first node of the list
     */
    public E removeFirst() {
        if (isEmpty())
            return null;
        return remove(head, "first");
    }

    /**
     * Removes the last node of the list
     * @return the element that was being stored at the last node of the list
     */
    public E removeLast() {
        if (isEmpty())
            return null;
        return remove(tail, "last");
    }

    /**
     * Removes the specified node from the list
     * @param node to be removed
     * @param position a string indicating which position, either first or last, the node should be removed from 
     * @return the element of the node that was removed
     */
    private E remove(Node<E> node, String position) {
        // Special case of removing the only remaining node
        if (size() == 1)
            removeTheOnlyNode();
        // Special case of removing the second to last remaining node - the single 
        //   remaining node will have to link back to itself in both directions
        else if (size() == 2) {
            if (position == "first") {
                tail.setNext(tail);
                tail.setPrev(tail);
                head = tail;
            } else {
                head.setNext(head);
                head.setPrev(head);
                tail = head;
            }
        }
        // Standard removal
        else {
            if (position == "first") {
                Node<E> prev = node.getPrev();
                Node<E> next = node.getNext();
                prev.setNext(next);
                next.setPrev(prev);
                head = next;
            } else {
                Node<E> newTail = tail.getPrev();
                newTail.setNext(head);
                head.setPrev(newTail);
            }       
        }
        size--;
        return node.getElement();
    }

    private void removeTheOnlyNode() {
        // Remove the only remaining node by creating temporary dummy nodes
        head = new Node<>(null, null, null);
        tail = new Node<>(null, head, tail);
        head.setNext(tail);
        head.setPrev(tail);
        size = 0;
    }

    /**
     * Returns a String representing the elements of this linked list
     * @return the string representation of this linked list
     */
    public String toString() {
        String list = "";
        Node<E> current = head;
        for (int i = 0; i < size(); i++) {
            list += current.getElement().toString() + ", ";
            current = current.getNext();
        }
        return list;
    }

    /**
     * Compares two CircularDoublyLinkedList objects - checks that the lists
     * have the same number of elements in the same order
     * @param o an Object
     * @return true if the two lists match, false if otherwise
     */
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (this.getClass() != o.getClass())
            return false;
        CircularDoublyLinkedList other = (CircularDoublyLinkedList)o;
        if (this.size() != o.size())
            return false;
        // CHECK THAT THE ELEMENTS IN EACH LIST ARE IN THE SAME ORDER !!!!!!!
        // started using textbook implementation
        Node<E> thisWalk = this.head;
        Node<E> otherWalk = o.head;
        return true;
    }

    public CircularDoublyLinkedList<E> clone() throws CloneNotSupportedException {
        // cloneeeeeee
        return this;
    }

    /**
     * Returns the node element at the given index position
     * @param index the index of the element desired
     * @return the element at the specified index
     */
    public E get(int index) {
        // Handle invalid index value
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException("Invalid index!");
        // Temp variable to walk through the list
        Node<E> current = head;
        for (int i = 0; i <= size(); i++)
            current = current.getNext();
        return current.getElement();
    }

    /**
     * Replaces the element at the specified index with the given element
     * @param index the index of the element to be replaced
     * @param element the element to be added to the list at position index
     * @return the element that was previously stored at the given index
     */
    public E set(int index, E element) {
        // Handle invalid index value
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException("Invalid index!");
        
        // see eclipse version
    }

    public void rotate() {

    }
}