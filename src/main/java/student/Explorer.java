package student;

import game.Edge;
import game.EscapeState;
import game.ExplorationState;
import game.InternalMinHeap;
import game.Node;
import game.NodeStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Explorer {

  /**
   * Explore the cavern, trying to find the orb in as few steps as possible.
   * Once you find the orb, you must return from the function in order to pick
   * it up. If you continue to move after finding the orb rather
   * than returning, it will not count.
   * If you return from this function while not standing on top of the orb,
   * it will count as a failure.
   *   
   * <p>There is no limit to how many steps you can take, but you will receive
   * a score bonus multiplier for finding the orb in fewer steps.</p>
   * 
   * <p>At every step, you only know your current tile's ID and the ID of all
   * open neighbor tiles, as well as the distance to the orb at each of these tiles
   * (ignoring walls and obstacles).</p>
   * 
   * <p>To get information about the current state, use functions
   * getCurrentLocation(),
   * getNeighbours(), and
   * getDistanceToTarget()
   * in ExplorationState.
   * You know you are standing on the orb when getDistanceToTarget() is 0.</p>
   *
   * <p>Use function moveTo(long id) in ExplorationState to move to a neighboring
   * tile by its ID. Doing this will change state to reflect your new position.</p>
   *
   * <p>A suggested first implementation that will always find the orb, but likely won't
   * receive a large bonus multiplier, is a depth-first search.</p>
   *
   * @param state the information available at the current state
   */
  public void explore(ExplorationState state)  {

      List<Long> previousNode = new ArrayList<>();
      Stack<Long> pathTaken = new Stack<>();
      pathTaken.add(state.getCurrentLocation());
      previousNode.add(state.getCurrentLocation());

      while (!(state.getDistanceToTarget() == 0)){
          Collection<NodeStatus> neighbours = state.getNeighbours();
          List<NodeStatus> list;
          list = new ArrayList<>();
          for (NodeStatus x : neighbours) {
              if (!previousNode.contains(x.getId())) {
                  list.add(x);
              }
          }
          NodeStatus n;
          long id;
          if (list.size()>0){
              List<NodeStatus> toSort = new ArrayList<>();
              toSort.addAll(list);
              toSort.sort(NodeStatus::compareTo);
              NodeStatus found = null;
              for (NodeStatus nodeStatus : toSort) {
                  found = nodeStatus;
                  break;
              }
              n = found;
              assert n != null;
              id = n.getId();
              previousNode.add(id);
              pathTaken.add(id);
          } else {
              pathTaken.pop();
              id = pathTaken.peek();
          }
          state.moveTo(id);
      }
  }

  /**
   * Escape from the cavern before the ceiling collapses, trying to collect as much
   * gold as possible along the way. Your solution must ALWAYS escape before time runs
   * out, and this should be prioritized above collecting gold.
   *
   * <p>You now have access to the entire underlying graph, which can be accessed 
   * through EscapeState.
   * getCurrentNode() and getExit() will return you Node objects of interest, and getVertices()
   * will return a collection of all nodes on the graph.</p>
   * 
   * <p>Note that time is measured entirely in the number of steps taken, and for each step
   * the time remaining is decremented by the weight of the edge taken. You can use
   * getTimeRemaining() to get the time still remaining, pickUpGold() to pick up any gold
   * on your current tile (this will fail if no such gold exists), and moveTo() to move
   * to a destination node adjacent to your current node.</p>
   * 
   * <p>You must return from this function while standing at the exit. Failing to do so before time
   * runs out or returning from the wrong location will be considered a failed run.</p>
   * 
   * <p>You will always have enough time to escape using the shortest path from the starting
   * position to the exit, although this will not collect much gold.</p>
   *
   * @param state the information available at the current state
   */
  public void escape(EscapeState state) {
    //Package-private implementation of Dijkstra's algorithm that returns
   //only the minimum distance between the given node and the target node for
   //this cavern (Currently implemented on Cavern.)
      InternalMinHeap<Node> node = new InternalMinHeap<>();
      Map<Node, Node> previousNode = new HashMap<>();

      // Contains an entry for each node
      Map<Long, Integer> pathWeights = new HashMap<>();
      Node originalPosition = state.getCurrentNode();

      pathWeights.put(originalPosition.getId(), 0);
      node.add(originalPosition, 0);

      while (!node.isEmpty()) {
          Node f = node.poll();

          if (state.getExit().equals(f)) {
              break;
          }

          int nWeight = pathWeights.get(f.getId());

          for (Edge edge : f.getExits()) {
              Node w = edge.getOther(f);
              int weightThroughN = nWeight + edge.length();
              Integer existingWeight = pathWeights.get(w.getId());

              if (existingWeight != null) {
                  if (weightThroughN < existingWeight) {
                      pathWeights.put(w.getId(), weightThroughN);
                      node.changePriority(w, weightThroughN);
                  }
              } else {
                  pathWeights.put(w.getId(), weightThroughN);
                  node.add(w, weightThroughN);
              }

              if (existingWeight == null || weightThroughN < existingWeight) {
                  previousNode.put(w, f);
              }
          }
      }

      List<Node> visitOrder = new ArrayList<>();
      Node u = state.getExit();

      while (u != null) {
          visitOrder.add(u);
          u = previousNode.get(u);
      }

      Collections.reverse(visitOrder);
      visitOrder.remove(0);

      for (Node n : visitOrder) {
          if (state.getCurrentNode().getTile().getGold() > 0) {
              state.pickUpGold();
          }
          state.moveTo(n);
      }
  }
}
