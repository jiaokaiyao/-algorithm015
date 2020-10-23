import java.util.ArrayList;
import java.util.List;

/**
 * @author : jiaokaiyao
 * @version V1.0
 * @Description: TODO 重排链表
 * @date Date : 2020年10月20日 10:08
 */
public class ReorderListSolution {

    class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }
   // TODO ======================== 线性表辅助解法=================================
    /**
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)
     * @param head
     */
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        // 线性链表转存 方便索引访问
        List<ListNode> helper = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            helper.add(node);
            node = node.next;
        }
        int i = 0, j = helper.size() - 1;
        while (i < j) {
            helper.get(i).next = helper.get(j);
            i++;
            // 当i==j时 交换已经完成（遇到同一节点） 跳出循环
            if (i == j) {
                break;
            }
            helper.get(j).next = helper.get(i);
            j--;
        }
        // 交换完之后  当前i所在的位置是链表中的尾结点，尾结点的后继节点为null
        helper.get(i).next = null;

    }

    // TODO ======================== 寻找链表中点 + 链表逆序 + 合并链表=================================
}
