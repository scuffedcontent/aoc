"""
21. Merge Two Sorted Lists

You are given the heads of two sorted linked lists list1 and list2.
Merge the two lists in a one sorted list. 
The list should be made by splicing together the nodes of the first two lists.
Return the head of the merged linked list.

Input: list1 = [1,2,4], list2 = [1,3,4]
Output: [1,1,2,3,4,4]


# Definition for singly-linked list.
 class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class My_Solution:
    def mergeTwoLists(self, list1: Optional[ListNode], list2: Optional[ListNode]) -> Optional[ListNode]:
        mergedNode = ListNode()
        tail = mergedNode

        while list1 and list2:
            if list1.val < list2.val:
                tail.next = list1
                list1 = list1.next
            else:
                tail.next = list2
                list2 = list2.next
            tail = tail.next
        
        if list1:
            tail.next = list1
        elif list2:
            tail.next = list2

        return mergedNode.next

class Fast_Solution:
    def mergeTwoLists(self, list1: Optional[ListNode], list2: Optional[ListNode]) -> Optional[ListNode]:
        nh = ListNode(val=-1)
        tp = nh
        
        while list1 and list2:
            if list1.val < list2.val:
                tp.next = list1
                list1 = list1.next
            else:
                tp.next = list2
                list2 = list2.next
            tp = tp.next
        if list1:
            tp.next = list1
        if list2:
            tp.next = list2
        return nh.next


class Faster_Solution:
    def mergeTwoLists(self, list1: Optional[ListNode], list2: Optional[ListNode]) -> Optional[ListNode]:
        result = curr = ListNode()
        i = list1
        j = list2
        while i and j:
            if i.val <= j.val:
                curr.next = i
                i = i.next
            else:
                curr.next = j
                j = j.next
            curr = curr.next
        if i:
            curr.next = i
        elif j:
            curr.next = j
        return result.next




class Fastest_Solution:
    def mergeTwoLists(self, list1: Optional[ListNode], list2: Optional[ListNode]) -> Optional[ListNode]:

        current1, current2 = list1, list2

        if current1 is None:
            return list2
        elif current2 is None:
            return list1
        
        final_node = None
        current_node = None
        if current1.val < current2.val:
            final_node = current1
            current_node = current1
            current1 = current1.next
        else:
            final_node = current2
            current_node = current2
            current2 = current2.next

        while current1 and current2:
            if current1.val < current2.val:
                current_node.next = current1
                current1 = current1.next
            else:
                current_node.next = current2
                current2 = current2.next
            
            current_node = current_node.next
        

        current_node.next = current1 if current1 else current2

        return final_node
                
"""