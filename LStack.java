// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

/**
 * Reference: This Linked stack implementation
 *  is directly taken from from OpenDSA code
 * from the section 5.8 Stacks
 * 
 * @author Kerem Bozgan kerembozgan
 * @version 2022-09-18
 */
// Linked stack implementation
class LStack<E> implements Stack<E> {
  private Link<E> top;            // Pointer to first element
  private int size;               // Number of elements

  // Constructors
  LStack() { top = null; size = 0; }
  LStack(int size) { top = null; size = 0; }

  // Reinitialize stack
  public void clear() { top = null; size = 0; }

// Put "it" on stack
  public boolean push(E it) {  
    top = new Link<E>(it, top);
    size++;
    return true;
  }

// Remove "it" from stack
  public E pop() {           
    if (top == null) { return null; }
    E it = top.element();
    top = top.next();
    size--;
    return it;
  }

  public E topValue() {      // Return top value
    if (top == null) { return null; }
    return top.element();
  }

  // Return stack length
  public int length() { return size; }
  
  // Tell if the stack is empty
  public boolean isEmpty() { return size == 0; }
}