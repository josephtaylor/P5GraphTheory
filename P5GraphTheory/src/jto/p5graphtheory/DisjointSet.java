package jto.p5graphtheory;

import java.util.LinkedList;

public class DisjointSet<E>
{
    private LinkedList<LinkedList<E>> sets;

    public DisjointSet() {
        sets = new LinkedList<LinkedList<E>>();
    }

    public void makeSet(E singleton) {
        LinkedList<E> newSet = new LinkedList<E>();
        newSet.add(singleton);
        sets.add(newSet);
    }

    public void union(E a, E b) {
        LinkedList<E> foundSet = null;
        LinkedList<E> appendedSet = null;
        for(LinkedList<E> set : sets) {
            if(set.contains(a)) {
                foundSet = set;
            }
            else if(set.contains(b)) {
                appendedSet = set;
            }
        }
        for(E element : appendedSet) {
            foundSet.add(element);
        }
        sets.remove(appendedSet);
    }

    public LinkedList<E> find(E element) {
        for(LinkedList<E> set : sets) {
            if(set.contains(element)) {
                return set;
            }
        }
        return null;
    }

}
