import java.util.Deque;
import java.util.LinkedList;

/**
 * @author kaiya
 * @Desc Deque addFirst addLast代码
 * @date 2020/8/28 21:29
 */
public interface DequeDemo {
    public static void main(String[] args) {
        // push 会放在现有队列第一个 addFist是加在现有队里的第一个
        Deque<String> deque = new LinkedList<String>();
        deque.push("b");
        deque.addFirst("a");
        deque.addFirst("f");
        deque.addFirst("i");
        deque.addFirst("k");
        deque.push("c");
        deque.addLast("n");
        deque.addLast("l");
        deque.addLast("m");
        System.out.println(deque);
        String peekFirst = deque.peekFirst();
        // 不改变结构获得第一个元素
        System.out.println("peekFirst:::"+ peekFirst);
        // 不改变结构获得最后一个元素
        String peekLast = deque.peekLast();
        System.out.println("peekLast:::"+ peekLast);
        String peek1 = deque.peek();
        System.out.println("first peek:::"+ peek1);
        String peek2 = deque.peek();
        System.out.println("twice peek:::"+ peek2);
        System.out.println(deque);
        System.out.println("deque pop start----------");
        while (deque.size() > 0) {
            System.out.println(deque.pop());
        }
        System.out.println(deque);
    }
}
