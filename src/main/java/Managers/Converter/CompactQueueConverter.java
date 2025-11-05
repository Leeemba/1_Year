package Managers.Converter;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;

import java.util.ArrayDeque;

public class CompactQueueConverter implements Converter {

    private final Mapper mapper;

    public CompactQueueConverter(Mapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public boolean canConvert(Class type) {
        return ArrayDeque.class.isAssignableFrom(type);
    }

    @Override
    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
        for (Object item : (ArrayDeque<?>) source) {
            if (item == null) {
                writer.startNode("element");
                writer.addAttribute("null", "true");
                writer.endNode();
            } else {
                // Получаем alias или имя класса
                String nodeName = mapper.serializedClass(item.getClass());
                writer.startNode(nodeName);
                if (!isSimpleType(item.getClass()) && !nodeName.equals(item.getClass().getName())) {
                    // alias уже есть, не пишем атрибут class
                } else if (!isSimpleType(item.getClass())) {
                    writer.addAttribute("class", item.getClass().getName());
                }
                context.convertAnother(item);
                writer.endNode();
            }
        }
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        ArrayDeque<Object> queue = new ArrayDeque<>();
        while (reader.hasMoreChildren()) {
            reader.moveDown();
            String isNull = reader.getAttribute("null");
            if ("true".equals(isNull)) {
                queue.add(null);
            } else {
                // Получаем настоящий класс из alias или class-атрибута
                String nodeName = reader.getNodeName();
                Class<?> clazz = mapper.realClass(nodeName);
                String classNameAttr = reader.getAttribute("class");

                if (classNameAttr != null) {
                    try {
                        clazz = Class.forName(classNameAttr);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException("Класс не найден: " + classNameAttr, e);
                    }
                }
                queue.add(context.convertAnother(null, clazz));
            }
            reader.moveUp();
        }
        return queue;
    }

    private boolean isSimpleType(Class<?> clazz) {
        return clazz.equals(String.class) ||
                Number.class.isAssignableFrom(clazz) ||
                clazz.equals(Boolean.class) ||
                clazz.isPrimitive();
    }
}
