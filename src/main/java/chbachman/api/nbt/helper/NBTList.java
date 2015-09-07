package chbachman.api.nbt.helper;

import java.util.AbstractList;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import chbachman.api.nbt.NBTSerializer;

public class NBTList<E> extends AbstractList<E> {

    public final NBTTagList list;
    public final NBTSerializer<E> type;

    public NBTList(NBTSerializer<E> type) {
        this(new NBTTagList(), type);
    }

    public NBTList(NBTTagList list, NBTSerializer<E> type) {
        this.list = list;
        this.type = type;
    }

    @Override
    public E get(int index) {
        NBTTagCompound nbt = list.getCompoundTagAt(index);

        if (nbt == null) {
            return null;
        }

        E data = (E) type.loadFromNBT(nbt);

        return data;

    }

    @Override
    public int size() {
        return list.tagCount();
    }

    @Override
    public void add(int index, E element) {

        if (element == null) {
            return;
        }

        this.list.appendTag(new NBTTagCompound());

        for (int i = list.tagCount() - 2; i >= index; i++) {
            list.func_150304_a(i + 1, list.getCompoundTagAt(i));
        }

        NBTTagCompound nbt = new NBTTagCompound();
        type.saveToNBT(element, nbt);

        this.list.func_150304_a(index, nbt);
    }

    @Override
    public E set(int index, E element) {

        E data = this.get(index);

        if (element == null) {
            return data;
        }

        NBTTagCompound nbt = new NBTTagCompound();
        type.saveToNBT(element, nbt);

        this.list.func_150304_a(index, nbt);

        return data;
    }

    @Override
    public E remove(int index) {

        E data = this.get(index);

        list.removeTag(index);

        return data;
    }
}
