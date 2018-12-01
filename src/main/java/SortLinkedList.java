public class SortLinkedList {

    private static class ListNode {
        ListNode next;
        Integer val;

        private ListNode(Integer x) {
            val = x;
            next = null;
        }
    }

    /**
     * 快速排序
     *
     * @param begin
     * @param end
     */
    public static void quickSort(ListNode begin, ListNode end) {
        if (begin == null || begin == end) {
            return;
        }

        ListNode index = partition(begin, end);
        quickSort(begin, index);
        quickSort(index.next, end);
    }

    private static ListNode partition(ListNode begin, ListNode end) {
        if (begin == null || end == null || begin == end) {
            return begin;
        }

        int val = begin.val;
        ListNode index = begin, cur = begin.next;

        while (cur != end) {
            // index指比val小的节点， cur指比val的节点
            // 当发现cur比基准值val小的值的时候, cur与index后面节点交换数值
            if (cur.val < val) {
                index = index.next;
                int temp = index.val;
                index.val = cur.val;
                cur.val = temp;
            }
            cur = cur.next;
        }

        begin.val = index.val;
        index.val = val;

        return index;
    }


    /**
     * 快速排序
     *
     * @param head
     * @return
     */
    public static ListNode mergeSort(ListNode head) {
        //空链表或者只有单个结点
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head, fast = head.next;

        //使用快慢指针寻找中间 结点
        while (fast != null && fast.next != null) {
            slow = slow.next;

            fast = fast.next.next;
        }

        // 将链表打断
        ListNode ptr1 = slow.next;
        slow.next = null;

        ListNode tmp1 = mergeSort(head);
        ListNode tmp2 = mergeSort(ptr1);
        return merge(tmp1, tmp2);
    }

    private static ListNode merge(ListNode start1, ListNode start2) {
        ListNode head = new ListNode(-1);
        ListNode pre = head;

        ListNode pre1 = start1, pre2 = start2;

        // 两个有序链表合并
        while (pre1 != null && pre2 != null) {
            if (pre1.val <= pre2.val) {
                pre.next = pre1;
                pre = pre.next;
                pre1 = pre1.next;
            } else {
                pre.next = pre2;
                pre = pre.next;
                pre2 = pre2.next;
            }
        }

        while (pre1 != null) {
            pre.next = pre1;
            pre = pre.next;
            pre1 = pre1.next;
        }

        while (pre2 != null) {
            pre.next = pre2;
            pre = pre.next;
            pre2 = pre2.next;
        }

        return head.next;
    }


    /**
     * 冒泡排序
     * @param head
     * @return
     */
    public static ListNode bubbleSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode cur = null, tail = null;

        cur = head;

        while (cur.next != tail) {
            while (cur.next != tail) {
                if (cur.val > cur.next.val) {
                    int tmp = cur.val;
                    cur.val = cur.next.val;
                    cur.next.val = tmp;
                }
                cur = cur.next;
            }

            //下一次遍历的尾结点是当前结点, 每次冒泡排序最后增加一位有序数值
            tail = cur;
            //遍历起始结点重置为头结点
            cur = head;
        }

        return head;
    }
}
