package togally.structure.list;

import com.alibaba.fastjson.JSON;
import com.togally.structure.list.List;
import com.togally.structure.list.array.ArrayList;
import com.togally.structure.list.linked.UndirectLinkedList;
import org.junit.Test;

public class ListTest {

    /**
     * 测试并集
     *
     * @param listA 合并集合
     * @param listB 被合并集合
     */
    public void testUnion(List<Integer> listA, List<Integer> listB) {
        Integer elem = null;
        int lengthB = listB.length();
        for (int i = 0; i < lengthB; i++) {
            elem = listB.get(i);
            if (!listA.contains(elem)) {
                listA.insert(elem);
            }
        }
    }

    @Test
    public void arrayListTest(){
        List<Integer> listA = new ArrayList<>();
        listA.insert(1);
        listA.insert(2);
        listA.insert(3);

        List<Integer> listB = new ArrayList<>();
        listB.insert(3);
        listB.insert(4);
        listB.insert(5);

        testUnion(listA,listB);
        System.out.println("testUnion" + JSON.toJSONString(listA));

        listA.clear();
        System.out.println("testUnion" + JSON.toJSONString(listA));
    }

    @Test
    public void undirectLinkedListTest(){
        List<Integer> listA = new UndirectLinkedList<>();
        listA.insert(1);
        listA.insert(2);
        listA.insert(3);

        List<Integer> listB = new UndirectLinkedList<>();
        listB.insert(3);
        listB.insert(4);
        listB.insert(5);

        testUnion(listA,listB);
        System.out.println("testUnion" + JSON.toJSONString(listA));

        listA.clear();
        System.out.println("testUnion" + JSON.toJSONString(listA));
    }
}
