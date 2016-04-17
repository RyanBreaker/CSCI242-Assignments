package csci242.assignments.madlib;

import java.util.Objects;

/**
 * An ArrayBag is a generic collection class that holds references to objects.
 *
 * <p>Limitations:
 * <ul>
 * <li>The capacity of an ArrayBag can change after it is created, but the
 * maximum capacity is limited by the amount of free memory on the machine.
 * The constructors add(), clone(), and union() will result in an OutOfMemory
 * when free memory is exausted.</li>
 * <li>An ArrayBags capacity cannot exceed the largest integer,
 * Integer.MAX_VALUE. Any attempt to create a larger capacity results in a
 * failure do to an arithmetic overflow.</li>
 * <li>Because of the slow (linear) algorithms of this class, large ArrayBags
 * have poor performance.</li>
 * </ul>
 *
 * <p>Note that all array allocations and/or resizes have the potential to
 * throw an OutOfMemotyError exception.
 *
 * <p>Also, note that the add() method works efficiently (without needing more
 * memory) until the capacity if the ArrayBag is reached. This is assumed in
 * all methods that resize the ArrayBag.
 *
 * @author Ryan Breaker
 * @edu.uwp.cs.242.course CSCI242 - Computer Science II
 * @edu.uwp.cs.242.section 001
 * @edu.uwp.cs.242.assignment 4
 * @bugs None
 */
public class ArrayBag<E> implements Cloneable {

    private static final int DEFAULT_INITIAL_CAPACITY = 10;
    private static final int MIN_EXPAND = 10;

    private int size = 0;
    private Object[] array;


    /**
     * Create a new ArrayBag that contains all of the elements from two other ArrayBags.
     *
     * @param b1 an ArrayBag to be combined.
     * @param b2 an ArrayBag to be combined.
     * @param <E> type of the ArrayBag.
     * @return a new ArrayBag that is the combination of b1 and b2.
     */
    public static <E> ArrayBag<E> combine(ArrayBag<E> b1, ArrayBag<E> b2) {
        ArrayBag<E> newBag = new ArrayBag<>(b1.size + b2.size);

        newBag.addAll(b1);
        newBag.addAll(b2);

        return newBag;
    }


    //region Constructors
    /**
     * Create an empty ArrayBag with initial capacity of
     * DEFAULT_INITIAL_CAPACITY.
     */
    public ArrayBag() {
        array = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    /**
     * Create an empty ArrayBag with the given initial capacity.
     * @param initialCapacity the initial capacity.
     */
    public ArrayBag(int initialCapacity) {
        if (initialCapacity > 0) {
            array = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            array = new Object[DEFAULT_INITIAL_CAPACITY];
        } else {
            throw new IllegalArgumentException("Illegal capacity: " +
                                                initialCapacity);
        }
    }
    //endregion


    @SuppressWarnings("unchecked")
    private E element(int index) {
        return (E) array[index];
    }

    private void rangeCheck(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " outside of bounds!");
        }
    }

    /**
     * Generate a copy of this ArrayBag.
     *
     * <p>Subsequent changes to the copy will not affect the original and
     * vice-versa.
     *
     * @return a copy of this ArrayBag.
     * @throws CloneNotSupportedException if the clone() method is not supported.
     */
    @Override
    @SuppressWarnings("unchecked")
    protected Object clone() throws CloneNotSupportedException {
        super.clone();

        ArrayBag<E> clone = new ArrayBag<>(getCapacity());

        clone.addMany((E[]) array);

        return clone;
    }

    /**
     * Reduce the current capacity of this ArrayBag to its size.
     *
     * <p>This method reduced this ArrayBag to its actual size, i.e., the
     * capacity is sized to being the number of elements it actually contains.
     */
    public void trimToSize() {
        // Don't do anything if we're already trimmed
        if (size == array.length) return;

        Object[] newArray = new Object[size];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    /**
     * Change the current capacity of this ArrayBag.
     *
     * <p>Note: if the capacity is already at or greater than the specified
     * minimum capacity, the capacity will be unchanged.
     *
     * @param minimumCapacity the new capacity for this ArrayBag.
     */
    public void ensureCapacity(int minimumCapacity) {
        // Don't do anything if we already have at least the capacity requested
        if (minimumCapacity <= array.length) return;

        Object[] newArray;

        if (minimumCapacity - array.length > MIN_EXPAND) {
            newArray = new Object[minimumCapacity];
        } else {
            newArray = new Object[array.length + MIN_EXPAND];
        }

        System.arraycopy(array, 0, newArray, 0, size);

        array = newArray;
    }

    /**
     * Accessor method to count the number of occurrences of a particular
     * element in this ArrayBag.
     *
     * <p>If the given target is non-null, then the occurrences are found using
     * the target.equals method.
     *
     * @param target the reference to an E object to be counted.
     * @return the number of times the given target occurs in this ArrayBag.
     */
    public int countOccurances(E target) {
        Objects.requireNonNull(target);

        int count = 0;

        for(int i = 0; i < size; i++) {
            count += (target.equals(element(i))) ? 1 : 0;
        }

        return count;
    }


    //region Add

    /**
     * Add a new element to the ArrayBag.
     *
     * <p>If this new element would take the ArrayBag beyond its current
     * capacity, then the capacity is increased before adding the new element.
     * Capacity is increased by doubling the current size.
     *
     * @param element the element to add.
     */
    public void add(E element) {
        ensureCapacity(size + 1);
        array[size++] = element;
    }

    /**
     * Add the contents of another ArrayBag to this ArrayBag.
     *
     * @param addend The ArrayBag whose contents will be added to this ArrayBag.
     */
    public void addAll(ArrayBag<E> addend) {
        for (int i = 0; i < addend.size; i++) {
            add(addend.element(i));
        }
    }

    /**
     * Add a variable number of new elements to this ArrayBag.
     *
     * <p>If the new elements would take this ArrayBag beyond its current
     * capacity, then the capacity will be increased before adding the new
     * elements.
     *
     * @param elements a variable number of new elements to add to the ArrayBag.
     */
    @SafeVarargs
    public final void addMany(E... elements) {
        for (E element : elements) {
            add(element);
        }
    }
    //endregion


    //region Remove

    /**
     * Remove a specified element from this ArrayBag.
     *
     * <p>If the target is found in this ArrayBag, then the element is removed.
     * If the target is not found, this ArrayBag remains unchanged.
     *
     * @param target the element to remove from the ArrayBag.
     * @return {@code true} if the element was removed, {@code false} otherwise.
     */
    public boolean remove(E target) {
        Objects.requireNonNull(target);

        for (int i = 0; i < size; i++) {
            if (target.equals(element(i))) {
                internalRemove(i);
                return true;
            }
        }

        return false;
    }

    private void internalRemove(int index) {
        // Number of elements to shift in the array
        int numMoved = size - index - 1;

        // If numMoved is 0, the element being removed is already at the end of
        // the array
        if (numMoved > 0) {
            // Otherwise shift them over such that the element to remove is at
            // the end
            System.arraycopy(array, index + 1, array, index, numMoved);
        }

        // Set last element to null for GC
        array[--size] = null;
    }
    //endregion


    //region Getters

    /**
     * Accessor method to retrieve a random element from this ArrayBag.
     *
     * <p>Once the element is retrieved from the ArrayBag, the element is
     * removed from the ArrayBag.
     *
     * @return a randomly selected element from this ArrayBag.
     * @throws IllegalStateException if the ArrayBag is empty.
     */
    public E grab() {
        if(isEmpty()) {
            throw new IllegalStateException("ArrayBag is empty.");
        }

        int index = (int) (Math.random() * size);
        E grabbedElement = element(index);

        internalRemove(index);

        return grabbedElement;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return array.length;
    }
    //endregion
}
