package com.company;

import java.util.*;

public class MyCollectionsRealization<E> implements Collection<E> {
    private final int INITIAL_CAPACITY = 10;
    private Object[] array;
    private int size = 0;
    private double capacityCoefficient = 1.5D;

    public MyCollectionsRealization(int capacity, int capacityCoefficient){
        this(capacity);
        this.capacityCoefficient = capacityCoefficient;
    }

    public MyCollectionsRealization(int capacity) {
        if(capacity<5){
            //do nothing
        }else {
            array = new Object[capacity];
        }
    }

    public MyCollectionsRealization() {
        array =  new Object[INITIAL_CAPACITY];
    }

    @Override
    public boolean add(E e) {
        if(array.length > size){
            array[size] = e;
        }else{
            array = Arrays.copyOf(array, (int) (size * capacityCoefficient));
            array[size + 1] = e;
        }
        size++;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (Object object : array) {
            if(object == o){
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private E object;
            private int index = -1;

            @Override
            public boolean hasNext() {
                try {
                    if (array[index + 1] != null) {
                        return true;
                    } else {
                        return false;
                    }
                }catch (IndexOutOfBoundsException e){
                    return false;
                }
            }

            //checked
            @Override
            public E next() {
                Object object = array[index + 1];
                index++;
                return (E) object;
            }
        };

    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array, array.length);
    }

    //checked
    @Override
    public <T> T[] toArray(T[] a) {
        for (int i = 0; i < Math.min(a.length, size()); i++) {
            a[i] = (T)array[i];
        }
        return a;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if(array[i] == o){
                for (int j = i; j < size - 1 ; j++) {
                    array[j] = array[j+1];
                }
                array[size] = null;
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object object : c) {
            if(!this.contains(object)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean result = false;

        for (E object : c) {
            if (this.add(object)){
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean result = false;
        for (Object object : c) {
                if (this.remove(object)) {
                    result = true;
                }
        }
        return result;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Object[] tempArray = new Object[array.length];
        int i = -1;
        for (Object object : array) {
            if(c.contains(object)){
                i++;
                tempArray[i] = object;
            }
        }
        boolean result = Arrays.equals(tempArray,array);

        array = tempArray;
        size = ++i;
        return !result;
    }

    @Override
    public void clear() {
        array = new Object[array.length];
        size = 0;
    }
    
}
