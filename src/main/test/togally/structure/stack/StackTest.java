package togally.structure.stack;

import com.togally.structure.stack.ArrayStack;
import com.togally.structure.stack.IStack;
import com.togally.structure.stack.LinkedStack;
import org.junit.Assert;
import org.junit.Test;


public class StackTest {

    @Test
    public void arrayStackTest(){
        test(new ArrayStack<>());
    }

    @Test
    public void linkedStackTest(){
        test(new LinkedStack<>());
    }

    /**
     * 通用测试方法
     *
     * @param stack 栈
     */
    public void test(IStack<Integer> stack) {
        // 初始化
        stack.init();
        Assert.assertNotNull(stack.getList());

        // 栈为空
        Assert.assertTrue(stack.stackEmpty());
        // 压栈
        stack.push(1);
        Assert.assertFalse(stack.stackEmpty());
        Assert.assertEquals(1, stack.stackLength());
        Assert.assertEquals(1, stack.getTop().intValue());

        // 压栈
        stack.push(2);
        Assert.assertEquals(2, stack.stackLength());
        Assert.assertEquals(2, stack.getTop().intValue());

        // 获取top后不减少栈长度
        Assert.assertEquals(2, stack.getTop().intValue());
        Assert.assertEquals(2, stack.stackLength());

        // 弹栈后长度减少
        Assert.assertEquals(2, stack.pop().intValue());
        Assert.assertEquals(1, stack.stackLength());

        // 清空栈数据清空
        stack.clearStack();
        Assert.assertEquals(0, stack.stackLength());

        // 栈销毁
        stack.destroyStack();
        Assert.assertNull(stack.getList());
    }
}
