
/**
 * @author kaiya
 * @Desc 合并两个有序链表
 * @date 2020/8/28 18:55
 */
public class MergeTwoSortList {

    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuffer sb = new StringBuffer();
            sb.append(val);
            String str = appendNext(this.next, sb);
            return str;
        }
        public String appendNext(ListNode next, StringBuffer sb) {
            if (next != null) {
                sb.append("->").append(next.val);
                appendNext(next.next, sb);
            }
            return sb.toString();
        }
    }

    public ListNode createListNode1(int gap) {
        ListNode next = new ListNode(1+gap, new ListNode(3 + gap, new ListNode(4 + gap, null)));
        return next;
    }

    public static void main(String[] args) {
        ListNode l1 = new MergeTwoSortList().createListNode1(0);
        System.out.println(l1);
        ListNode l2 = new MergeTwoSortList().createListNode1(1);
        System.out.println(l2);
        ListNode merge = mergeTwoLists(l1, l2);
        System.out.println(merge);
    }


    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 边界处理
        if (l1 == null && l2 != null) {
            return l2;
        }
        if (l2 == null && l1 != null) {
            return l1;
        }
        if (l1 == null && l2 == null) {
            return null;
        }
        // 递归处理
        // 找出两个链表中头结点较小的那个链表，将此节点与余下的所有节点合并即可
        // 时间复杂度：O(m+n) 其中m和n分别是两个链表的各自长度
        // 空间复杂度：O(m+n) 其中m和n分别是两个链表的各自长度

        if(l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next) ;
            return l2;
        }

    }

}
