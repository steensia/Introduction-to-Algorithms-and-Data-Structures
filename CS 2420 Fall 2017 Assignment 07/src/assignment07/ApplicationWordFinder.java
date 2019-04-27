package assignment07;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * <p>
 * I wrote a small application to help me find letters for
 * a midterm problem (the tree problem).  I wanted to find
 * an interesting English word that when inserted into a
 * BST, would give English words for each traversal.  I
 * used my SearchTree class to help me search through a list
 * of words.  (It made an OK initial test.)  Ultimately,
 * the test yielded unsatisfactory words, but I evenutally
 * settled on a word that everyone would recognize when they
 * saw it in the post-order traversal.
 * </p>
 * 
 * <p>
 * This was intended to be a throw-away application, but I've
 * decided to put it in the files I give to students.  It's junk
 * but it does use the SearchTree class.
 * </p>
 *  
 * @author Peter Jensen
 * @version November 7, 2017
 */
public class ApplicationWordFinder
{
    /**
     * <p>
     * Application entry point.
     * </p>
     * 
     * @param args unused
     * @throws IOException if "words.txt" is not found in the project root
     */
    
    public static void main (String[] args) throws IOException
    {
        // Read all the words into a set.  Skip words that are
        //   not all lowercase letters, or that are too short.
        
        Scanner s = new Scanner(new File("words.txt"));
        Set<String> words = new TreeSet<String>();
        
        scan: while (s.hasNext())
        {
            String word = s.next();
            if (word.length() < 7)
                continue;
            
            for (int i = 0; i < word.length(); i++)
                if (!Character.isLowerCase(word.charAt(i)))
                    continue scan;

            words.add(word);
        }
        s.close();
                
        // Filter words for ones that meet my criteria for the exam.
        //   (No duplicate letters, etc.)  Loops once for each word.
        
        filter: for (String word : words)
        {            
            // Create a non-balanced search tree object to hold characters.
            //   (Starts out empty each time through the loop.)
            
            SearchTree<Character> st = new SearchTree<Character>((a,b) -> a.compareTo(b), false);
            
            // Add the letters of the current word to the search tree.
            //   If there are duplicate characters, skip this word.
            //   The search tree gets built in the order that the letters
            //   appear in the word.  (A pre-order traversal would
            //   yield back the original word.)
            
            // Originally, I added the letters in order.  (The pre-order traversal
            //   would match the word.)  Now, I add the letters in reverse order.
            //   (The post-order traversal matches the word.)
            
            for (int i = 0; i < word.length(); i++)    
            {
                char ch = word.charAt(word.length()-1-i);
                if (st.find(ch).size() > 0)
                    continue filter;
                
                st.add(ch);
            }
            
            // Make an builder object for collecting characters.
            
            final StringBuilder builder = new StringBuilder();
            
            // Make a visitor that will append characters to the builder.
            
            Visitor<Node<Character>> v = n -> builder.append(n.data);
            
            // Do a pre-order visit of the search tree to collect characters
            
            builder.setLength(0);
            st.visitPreOrder(v);
            String pre = builder.toString();
            
            // Do a post-order visit of the search tree to collect characters

            builder.setLength(0);
            st.visitPostOrder(v);
            String post = builder.toString();
            
            // Do an in-order visit of the search tree to collect characters
            
            builder.setLength(0);
            st.visitInOrder(v);
            String in = builder.toString();
            
            // Make sure none of the traversals match - it would make an uninteresting
            //   midterm question if traversals matched.  It would also indicate
            //   a non-interesting tree (probably a chain).
            
            if (pre.equals(post) || pre.equals(in) || post.equals(in))
                continue;
            
            // I tried all words to see if I could find one where all three
            //   traversals were words.  I couldn't find one.  I resorted to
            //   searching for words where the post-order traversal of the
            //   word (after insertion into the search tree) was also a word.
            
            // This may be axiomatic at this point.  Still, it was fun, and it
            //   used my SearchTree class (which helped me find bugs).  
            // Change to 'pre' or 'in' for other interesting results.
            
            if (!words.contains(post)) 
                continue;
            
            // Some trees were too tall.  I rejected these as well.
            
            if(st.getHeight() > 4)
                continue;
            
            // Print out the words that pass my filter, along with their
            //   traversals.  I hand-selected a word from this final list.
                
             System.out.println(word + " " + pre + " " + in + " " + post);
        }
        
        

    }

}
