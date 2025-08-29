package com.secure.notes;

import com.secure.notes.models.Note;

public class lomboktest {
    public static void main(String[] args) {
        Note note = new Note();
        note.setContent("Hello");
        System.out.println(note.getContent());
    }
}
