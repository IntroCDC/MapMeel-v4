package br.com.introcdc.mapmeelv4.utils;

import java.lang.reflect.Field;
import java.util.*;

public class ReflectionManager {

    private Class<?> clazz;
    private Object instance;

    public void begin(Class<?> clazz, Object instance) {
        this.clazz = clazz;
        this.instance = instance;
    }

    public void begin(Object instance) {
        this.clazz = instance.getClass();
        this.instance = instance;
    }

    public byte getByte(String field) {
        return (byte) this.getObject(field);
    }

    public <T> Collection<T> getCollection(String field) {
        return (Collection<T>) this.getObject(field);
    }

    public String getDouble(String field) {
        return (String) this.getObject(field);
    }

    public float getFloat(String field) {
        return (float) this.getObject(field);
    }

    public int getInteger(String field) {
        return (int) this.getObject(field);
    }

    public <T> List<T> getList(String field) {
        return (List<T>) this.getObject(field);
    }

    public long getLong(String field) {
        return (long) this.getObject(field);
    }

    public <K, V> Map<K, V> getMap(String field) {
        return (Map<K, V>) this.getObject(field);
    }

    public Object getObject(String field) {
        try {
            Field fieldObject = this.clazz.getDeclaredField(field);
            fieldObject.setAccessible(true);
            return fieldObject.get(this.instance);
        } catch (Exception exception) {
            System.out.println("[GET] Reflection fail " + this.toString() + ": " + exception.toString());
        }
        return null;
    }

    public <T> Set<T> getSet(String field) {
        return (Set<T>) this.getObject(field);
    }

    public String getString(String field) {
        return (String) this.getObject(field);
    }

    public UUID getUniqueId(String field) {
        return (UUID) this.getObject(field);
    }

    public void setByte(String field, byte value) {
        this.setObject(field, value);
    }

    public void setDouble(String field, double value) {
        this.setObject(field, value);
    }

    public void setFloat(String field, float value) {
        this.setObject(field, value);
    }

    public void setInteger(String field, int value) {
        this.setObject(field, value);
    }

    public void setLong(String field, long value) {
        this.setObject(field, value);
    }

    public void setObject(String field, Object value) {
        try {
            Field fieldObject = this.clazz.getDeclaredField(field);
            fieldObject.setAccessible(true);
            fieldObject.set(this.instance, value);
        } catch (Exception exception) {
            System.out.println("[SET] Reflection fail " + this.toString() + ": " + exception.toString());
        }
    }

    public void setString(String field, String value) {
        this.setObject(field, value);
    }

    public void setUniqueId(String field, UUID value) {
        this.setObject(field, value);
    }
}
