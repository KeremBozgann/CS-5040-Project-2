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
 * Reference: Stack interface
 *  is directly taken from from OpenDSA code
 * from the section 5.8 Stacks
 * 
 * @author Kerem Bozgan kerembozgan
 * @version 2022-09-18
 */
public interface Stack<E> { // Stack class ADT
  // Reinitialize the stack.
  public void clear();

  // Push "it" onto the top of the stack
  public boolean push(E it);

  // Remove and return the element at the top of the stack
  public E pop();

  // Return a copy of the top element
  public E topValue();

  // Return the number of elements in the stack
  public int length();
  
  // Tell if the stack is empty or not
  public boolean isEmpty();
}