public interface FibWinHelper {
	/**
	 * Allocates an internal array <kbd>fib</kbd> of given size.
	 *
	 * @param size the size of the internal array <kbd>fib</kbd> to be allocated
	 */
	void allocateArray(int size);

	/**
	 * Returns the value of <kbd>fib[index]</kbd> iff this array has been allocated previously using {@link #allocateArray(int)} and is long enough.
	 *
	 * @param index the index of the value to be returned from the internal array <kbd>fib</kbd>
	 * @return the current value of <kbd>fib[index]</kbd> in the internal array <kbd>fib</kbd>
	 */
	long getArrayValue(int index);

	/**
	 * Stores <kbd>newValue</kbd> at <kbd>fib[index]</kbd> iff this array has been allocated previously using {@link #allocateArray(int)} and is long enough.
	 *
	 * @param index    the index of the internal array <kbd>fib</kbd> where to store the new value
	 * @param newValue the new value to be stored at <kbd>fib[index]</kbd> in the internal array <kbd>fib</kbd>
	 */
	void setArrayValue(int index, long newValue);
}