/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package barraprogreso;

/**
 *
 * @author Ingeniero en Computación: Ricardo Presilla
 */
class LongTask {
    private int lengthOfTask;
    private int actual = 0;
    private String statMessage;
    LongTask () {
        //compute length of task ...
        //in a real program, this would figure out
        //the number of bytes to read or whatever
        lengthOfTask = 1000;
    }
    //called from ProgressBarDemo to start the task
    void go() {
        actual = 0;
        final SwingWorker worker = new SwingWorker() {
            public Object construct() {
                return new ActualTask();
            }
        };
    }
    //called from ProgressBarDemo to find out how much work needs to be done
    int getLengthOfTask() {
        return lengthOfTask;
    }
    //called from ProgressBarDemo to find out how much has been done
    int getCurrent() {
        return actual;
    }
    void stop() {
        actual = lengthOfTask;
    }
    //called from ProgressBarDemo to find out if the task has completed
    boolean done() {
        if (actual >= lengthOfTask)
            return true;
        else
            return false;
    }
    String getMessage() {
        return statMessage;
    }
    //the actual long running task, this runs in a SwingWorker thread
    class ActualTask {
        ActualTask () {
            //fake a long task,
            //make a random amount of progress every second
            while (actual < lengthOfTask) {
                try {
                    Thread.sleep(1000); //sleep for a second
                    actual += Math.random() * 100; //make some progress
                    statMessage = "Completado " + actual +
                                  " de " + lengthOfTask + ".";
                } catch (InterruptedException e) {  }
            }
        }
    }
}

