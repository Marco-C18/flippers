package com.dds.flippers.memento;

import java.util.Stack;

public class ClassCaretaker {
    private final Stack<ClassMemento> record = new Stack<>();

    public void saveMemento(ClassMemento memento) {
        record.push(memento);
    }

    public ClassMemento restore() {
        return record.isEmpty() ? null : record.pop();
    }

    public boolean history() {
        return !record.isEmpty();
    }

}
