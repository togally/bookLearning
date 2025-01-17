package togally.structure.list;

import com.alibaba.fastjson.JSON;
import com.togally.structure.list.List;
import com.togally.structure.list.array.ArrayList;
import com.togally.structure.list.linked.CircleLinkedList;
import com.togally.structure.list.linked.DulCircleLinkedList;
import com.togally.structure.list.linked.DulSinglyLinkedList;
import com.togally.structure.list.linked.node.Node;
import com.togally.structure.list.linked.SinglyLinkedList;
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

    /**
     * 数组线性表
     */
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

    /**
     * 单向链表
     */
    @Test
    public void singlyLinkedListTest(){
        List<Integer> listA = new SinglyLinkedList<>();
        listA.insert(1);
        listA.insert(2);
        listA.insert(3);

        List<Integer> listB = new SinglyLinkedList<>();
        listB.insert(3);
        listB.insert(4);
        listB.insert(5);

        testUnion(listA,listB);
        System.out.println("testUnion" + JSON.toJSONString(listA));

        listA.clear();
        System.out.println("testUnion" + JSON.toJSONString(listA));
    }

    /**
     * 循环链表
     */
    @Test
    public void circleLinkedListTest(){
        CircleLinkedList<Integer, Node<Integer>> listA = new CircleLinkedList<>();
        listA.insert(1);
        listA.insert(2);
        listA.insert(3);

        CircleLinkedList<Integer,Node<Integer>> listB = new CircleLinkedList<>();
        listB.insert(3);
        listB.insert(4);
        listB.insert(5);

        listA.union(listB);
        System.out.println("testUnion" + JSON.toJSONString(listA));

        listA.clear();
        System.out.println("testUnion" + JSON.toJSONString(listA));
    }

    /**
     * 循环链表
     */
    @Test
    public void dualSinglyLinkedList(){
        DulSinglyLinkedList<Integer> listA = new DulSinglyLinkedList<>();
        listA.insert(1);
        listA.insert(2);
        listA.insert(3);

        DulSinglyLinkedList<Integer> listB = new DulSinglyLinkedList<>();
        listB.insert(3);
        listB.insert(4);
        listB.insert(5);

        testUnion(listA,listB);
        System.out.println("testUnion" + JSON.toJSONString(listA));

        listA.clear();
        System.out.println("testUnion" + JSON.toJSONString(listA));
    }


    /**
     * 循环链表
     */
    @Test
    public void dualCircleLinkedList(){
        DulCircleLinkedList<Integer> listA = new DulCircleLinkedList<>();
        listA.insert(1);
        listA.insert(2);
        listA.insert(3);

        DulCircleLinkedList<Integer> listB = new DulCircleLinkedList<>();
        listB.insert(3);
        listB.insert(4);
        listB.insert(5);

        listA.union(listB);
        System.out.println("testUnion" + JSON.toJSONString(listA));

        listA.clear();
        System.out.println("testUnion" + JSON.toJSONString(listA));
    }
}
