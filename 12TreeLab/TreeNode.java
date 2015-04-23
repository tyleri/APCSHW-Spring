public class TreeNode<T> {

    private TreeNode<T> left;
    private TreeNode<T> right;
    private T data;

    public TreeNode() {}

    public TreeNode(T data) {
        this.data = data;
    }

    public TreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public T getData() {
        return data;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setLeft(TreeNode<T> tn) {
        left = tn;
    }

    public void setRight(TreeNode<T> tn) {
        right = tn;
    }
}
