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
 * Reference: Link implementation
 *  is taken from from OpenDSA code
 * from the section 5.4 Linked Lists
 * 
 * @author Kerem Bozgan kerembozgan
 * @version 2022-09-25
 */

class Link<E> {         // Singly linked list node class
  private E e;          // Value for this node
  private Link<E> n;    // Point to next node in list

  // Constructors
  Link(E it, Link<E> inn) { e = it; n = inn; }
  Link(Link<E> inn) { e = null; n = inn; }

  E element() { return e; }                        // Return the value
  E setElement(E it) { return e = it; }            // Set element value
  Link<E> next() { return n; }                     // Return next link
  Link<E> setNext(Link<E> inn) { return n = inn; } // Set next link
}