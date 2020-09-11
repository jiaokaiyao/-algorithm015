import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author kaiya
 * @Desc 二叉树的序列化与反序列化
 * @date 2020/9/11 16:34
 */
public class BSTSerializeAndDeserializeSolution {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return mySerialize(root, "");
    }

    /**
     * 深度优先遍历编码二叉树， 时间复杂度：O(N) 每个节点都需要访问一次 空间复杂度：O(n)
     * @param root
     * @param path
     * @return
     */
    private String mySerialize(TreeNode root, String path) {
        // 空节点用N表示
        if (root == null) {
            path += "N,";
            return path;
        }
        // 前序遍历【深度优先遍历】
        path = path + (root.val + ",");
        path = mySerialize(root.left, path);
        path = mySerialize(root.right, path);
        return path;
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String []dataArr = data.split(",");
        List<String> dataList = new LinkedList<>(Arrays.asList(dataArr));
        return myDeserialize(dataList);

    }

    /**
     * 依次构造根节点、左子树和右子树  时间复杂度：O(N) 每个节点都需要访问一次 空间复杂度：O(n)
     * @param dataList
     * @return
     */
    private TreeNode myDeserialize(List<String> dataList) {
        if (dataList.get(0) == null || dataList.get(0).equals("N")) {
            dataList.remove(0);
            return null;
        }
        // 构造根节点
        TreeNode root = new TreeNode(Integer.valueOf(dataList.get(0)));
        dataList.remove(0);
        // 构造左子树
        root.left = myDeserialize(dataList);
        // 构造右子树
        root.right = myDeserialize(dataList);
        return root;
    }
}
