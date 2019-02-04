// Dafne Culha - McGill ID: 260785524

package assignment4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class MyHashTable<K,V> implements Iterable<HashPair<K,V>>{
	// num of entries to the table
	private int numEntries;
	// num of buckets 
	private int numBuckets;
	// load factor needed to check for rehashing 
	private static final double MAX_LOAD_FACTOR = 0.75;
	// ArrayList of buckets. Each bucket is a LinkedList of HashPair
	private ArrayList<LinkedList<HashPair<K,V>>> buckets; 

	// constructor
	public MyHashTable(int initialCapacity) {
		// ADD YOUR CODE BELOW THIS

		this.numBuckets = initialCapacity;
		this.numEntries = 0;
		this.buckets = new ArrayList<LinkedList<HashPair<K,V>>>();

		for(int k = 0; k < initialCapacity; k++){
			this.buckets.add(new LinkedList<HashPair<K,V>>());
		}

		//ADD YOUR CODE ABOVE THIS
	}

	public int size() {
		return this.numEntries;
	}

	public int numBuckets() {
		return this.numBuckets;
	}

	/**
	 * Returns the buckets variable. Useful for testing  purposes.
	 */
	public ArrayList<LinkedList< HashPair<K,V> > > getBuckets(){
		return this.buckets;
	}
	/**
	 * Given a key, return the bucket position for the key. 
	 */
	public int hashFunction(K key) {
		int hashValue = Math.abs(key.hashCode())%this.numBuckets;
		return hashValue;
	}
	/**
	 * Takes a key and a value as input and adds the corresponding HashPair
	 * to this HashTable. Expected average run time  O(1)
	 */
	public V put(K key, V value) {

		//  ADD YOUR CODE BELOW HERE
		
	
		int index = hashFunction(key);
		HashPair<K,V> input = new HashPair<K,V>(key, value);

		V result = null;
 
		if (buckets.get(index).isEmpty()){

			buckets.get(index).addFirst(input);

			numEntries++;
			double load = (double)(this.numEntries)/this.numBuckets;

			if (load > MAX_LOAD_FACTOR){
				this.rehash();
				return null;
			}
			else {
				return null;
			}
		}

		else {

			Iterator<HashPair<K,V>> myIterator = this.buckets.get(index).iterator();
			while(myIterator.hasNext()){
				HashPair<K,V> current = myIterator.next();

				if(current.getKey().equals(key)){
					result = current.getValue();
					current.setValue(value);
					return result;
				}

			}

			this.buckets.get(index).addLast(input);
			numEntries++;

			double load = (double)(this.numEntries)/this.numBuckets;

			if (load > MAX_LOAD_FACTOR){
				this.rehash();
				return null;
			}
			else {
				return null;
			}
		}
		//  ADD YOUR CODE ABOVE HERE  		
	}



	/**
	 * Get the value corresponding to key. Expected average runtime = O(1)
	 */

	public V get(K key) {
		
		//ADD YOUR CODE BELOW HERE
		int index = hashFunction(key);
		V result;

		if (this.buckets.get(index).isEmpty()){
			return null;
		}
		else {
			Iterator<HashPair<K,V>> myIterator = this.buckets.get(index).iterator();

			while (myIterator.hasNext()){
				HashPair<K,V> current = myIterator.next();
				if(current.getKey().equals(key)){
					result = current.getValue();
					return result;
				}
			}
			return null;	
		}
		//ADD YOUR CODE ABOVE HERE
	}

	/**
	 * Remove the HashPair corresponding to key . Expected average runtime O(1) 
	 */
	public V remove(K key) {
		//ADD YOUR CODE BELOW HERE

		V result = null;
		int index = hashFunction(key);

		if (buckets.get(index).isEmpty()){
			return null;
		}

		else {
			
			Iterator<HashPair<K,V>> myIterator = this.buckets.get(index).iterator();

			while(myIterator.hasNext()){
				HashPair<K,V> current = myIterator.next();
				if(current.getKey().equals(key)){
					result = current.getValue();
					this.buckets.get(index).remove(current);
					return result;
				}
			}
			return null;
		}

		//ADD YOUR CODE ABOVE HERE
	}

	/**public int newHashFunction(K key) {
        int hashValue = Math.abs((key.hashCode())%this.numBuckets)*2;
        return hashValue;
    }
	 **/

	// Method to double the size of the hashtable if load factor increases
	// beyond MAX_LOAD_FACTOR.
	// Made public for ease of testing.

	public void rehash() {
		//ADD YOUR CODE BELOW HERE

		int new_size =this.buckets.size()*2;


		MyHashTable<K, V> newHT = new  MyHashTable<K, V>(new_size);


		for(LinkedList<HashPair<K, V>> currentBucket : this.buckets){
			for(HashPair<K,V> currentPair : currentBucket){
				newHT.put(currentPair.getKey(), currentPair.getValue());
			}
		}

		this.buckets = newHT.buckets;
		this.numBuckets = new_size;


		return;
		//ADD YOUR CODE ABOVE HERE
	}


	/**
	 * Return a list of all the keys present in this hashtable.
	 */

	public ArrayList<K> keys() {
		//ADD YOUR CODE BELOW HERE

		ArrayList<K> list_of_keys = new ArrayList<K>();

		for (LinkedList<HashPair<K, V>> currentBucket : this.buckets){
			for (HashPair<K,V> currentPair : currentBucket){
				list_of_keys.add(currentPair.getKey());

			}
		}
		return list_of_keys;

		//ADD YOUR CODE ABOVE HERE
	}

	/**
	 * Returns an ArrayList of unique values present in this hashtable.
	 * Expected average runtime is O(n)
	 */
	
	public ArrayList<V> values() {
		//ADD CODE BELOW HERE

		ArrayList<V> list_of_values = new ArrayList<V>();

		for(LinkedList<HashPair<K, V>> currentBucket : this.buckets){
			for(HashPair<K,V> currentPair : currentBucket){
				list_of_values.add(currentPair.getValue());

			}
		}

		return list_of_values;

		//ADD CODE ABOVE HERE
	}


	@Override
	public MyHashIterator iterator() {
		return new MyHashIterator();
	}


	private class MyHashIterator implements Iterator<HashPair<K,V>> {

		private LinkedList<HashPair<K,V>> entries;

		//constructor
		private MyHashIterator() {
			//ADD YOUR CODE BELOW HERE
			
			entries = new LinkedList<> ();
			
			for (LinkedList<HashPair<K,V>> hashPairs: buckets) {
				for (HashPair<K,V> currentHashPair: hashPairs)
					entries.add(currentHashPair);
			}
			//ADD YOUR CODE ABOVE HERE
		}

		@Override
		public boolean hasNext() {
			//ADD YOUR CODE BELOW HERE
			if(entries.isEmpty()) 
				return false;
			else 
				return true;
			//ADD YOUR CODE ABOVE HERE
		}

		@Override
		public HashPair<K,V> next() {
			//ADD YOUR CODE BELOW HERE
			return entries.removeFirst();
			//ADD YOUR CODE ABOVE HERE
		}
	}
}
