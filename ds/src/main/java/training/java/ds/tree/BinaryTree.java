package training.java.ds.tree;

public class BinaryTree<T extends Comparable<T>> implements Tree<T> {
  private Node<T> root;
  private int  size;
  
  public BinaryTree() { this.root = null;}
  
  public Node<T> getRoot() { return this.root;}
  
  @Override
  public int size() { return size;}
  
  @Override
  public void add(T item) {
    Node<T> node = new Node<T>(item);
    
    if (this.root == null) {
      this.root = node;
      System.out.println("Set root: " + node.getItem());
      size++;
    } else {
      insert(this.root, node);
    }
  }
  
  @Override
  public void addAll(T[] items) {
    for(T item: items) {
      add(item);
    }
  }
  
  @Override
  public boolean contains(T item) {
    Node<T> currentNode = get(item);
    return currentNode != null;
  }
  
  @Override
  public boolean remove(T item) {
    boolean deleted = false;
    
    if(this.root == null) {
      return false;
    }
    
    Node<T> currentNode = get(item);
    
    if(currentNode != null) {
      if(currentNode.getLeft() == null && currentNode.getRight() == null) {
        // the node to remove doesnt have any children
        unlink(currentNode, null);
        deleted = true;
      } else if(currentNode.getLeft() == null && currentNode.getRight() != null) {
        // the node to remove has a right child
        unlink(currentNode, currentNode.getRight());
        deleted = true;
      } else if(currentNode.getLeft() != null && currentNode.getRight() == null) {
        // the node to remove has a left child
        unlink(currentNode, currentNode.getLeft());
        deleted = true;
      } else {
        // the node to remove has both children
        Node<T> child = currentNode.getLeft();
        while(child.getRight() != null && child.getLeft() != null) {
          child = child.getRight();
        }
        
        child.getParent().setRight(null);
        child.setLeft(currentNode.getLeft());
        child.setRight(currentNode.getRight());
        unlink(currentNode, child);
        deleted = true;
      }
    }
    
    if(deleted) this.size--;
    return deleted;
  }
  
  public void clear() { }
  
  private void unlink(Node<T> currentNode, Node<T> newNode) {
    if(currentNode == this.root) {
      newNode.setLeft(currentNode.getLeft());
      newNode.setRight(currentNode.getRight());
      this.root = newNode;
    } else if(currentNode.getParent().getRight() == currentNode) {
      currentNode.getParent().setRight(newNode);
    } else {
      currentNode.getParent().setLeft(newNode);
    }
  }
  
  private Node<T> get(T item) {
    Node<T> currentNode = this.root;
    while (currentNode != null) {
      int val = item.compareTo(currentNode.getItem());
      
      if (val == 0) {
        return currentNode;
      } else if (val < 0) {
        currentNode = currentNode.getLeft();
      } else {
        currentNode = currentNode.getRight();
      }
    }
    return null;
  }
  
  private void insert(Node<T> parent, Node<T> child) {
    
    if ((child.getItem()).compareTo(parent.getItem()) < 0) {
      if (parent.getLeft() == null) {
        parent.setLeft(child);
        child.setParent(parent);
        size++;
      } else {
        insert(parent.getLeft(), child);
      }
    } else if ((child.getItem()).compareTo(parent.getItem()) > 0) {
      if (parent.getRight() == null) {
        parent.setRight(child);
        child.setParent(parent);
        size++;
      } else {
        insert(parent.getRight(), child);
      }
    }
    
    // dont anything when parent and child happen to be equal
    // data in binary tree is assumed to be unique and the value already exists in
    // the tree
  }
}

