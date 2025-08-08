package com.example.organizadortareas.model;

public interface ISerializableFileHandler {
    public void serialize(String filename, Object object);
    public Object deserialize(String filename);
}
