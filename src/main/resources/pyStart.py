import subprocess
import os
from datetime import datetime
import logging
import shlex

# Create and configure logger
logging.basicConfig(filename="pythonExecution.log",
                    format='%(asctime)s %(message)s',
                    filemode='w')

# Creating an object
logger = logging.getLogger()

# Setting the threshold of logger to DEBUG
logger.setLevel(logging.INFO)


logger.info("Inside init.....")
logger.info(datetime.now())
os.chmod("/Users/sky/Desktop/java_code/test1/target/classes/perm.sh", 0o775)
subprocess.call(shlex.split('sh perm.sh'))
#subprocess.call('./run_cmd.sh')
logger.info("End of init script")
