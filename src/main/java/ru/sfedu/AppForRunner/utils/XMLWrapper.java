package ru.sfedu.AppForRunner.utils;


import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "Lists")
public class XMLWrapper<T> {
    @ElementList(inline = true, required = false)
    public List<T> list;

    public XMLWrapper() {};

    public XMLWrapper(List<T> list) {
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
