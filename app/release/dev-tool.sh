#!/usr/bin/env bash

window=false
if [ "$OSTYPE" = "msys" ] ; then
  window=true;
elif [[ "$OSTYPE" == "cygwin" ]]; then
  window=true;
elif [[ "$OSTYPE" == "win32" ]]; then
  window=true;
fi

function has_opt() {
  OPT_NAME=$1
  shift
  #Par the parameters
  for i in "$@"; do
    # shellcheck disable=SC2053
    if [[ $i == $OPT_NAME ]] ; then
      echo "true"
      return
    fi
  done
  echo "false"
}

function get_opt() {
  OPT_NAME=$1
  DEFAULT_VALUE=$2
  shift

  #Par the parameters
  for i in "$@"; do
    # shellcheck disable=SC2004
    index=$(($index+1))
    if [[ $i == $OPT_NAME* ]] ; then
      value="${i#*=}"
      echo "$value"
      return
    fi
  done
  echo $DEFAULT_VALUE
}

bin=`dirname "$0"`
bin=`cd "$bin"; pwd`

echo "bin dir $bin"

JAVACMD="$JAVA_HOME/bin/java"
APP_HOME=`cd $bin/..; pwd; cd $bin`

# create log folder
mkdir -p $APP_HOME/logs

# library in project
LIB="$APP_HOME:$APP_HOME/lib/*:$APP_HOME/lib/spring/*:$APP_HOME/lib/hibernate/*:$APP_HOME/lib/jetty/*"
CLASSPATH="${CLASSPATH}:$LIB:$APP_HOME/config"

if $window; then
  JAVA_HOME=`cygpath --absolute --windows "$JAVA_HOME"`
  CLASSPATH=`cygpath --path --windows "$CLASSPATH"`
  APP_HOME=`cygpath --path --windows "$APP_HOME"`
fi

function start() {
  DATETIME=$(date '+%Y/%m/%d@%H:%M:%S')
  PROFILE=$(get_opt --profile 'console' $@)

  CONFIG_FILES="file:$APP_HOME/config/application.yaml"

  JAVA_OPTS="-server -XX:+UseParallelGC -Xshare:auto -Xms128m -Xmx1024m -Dfile.encoding=UTF-8"
  JAVA_OPTS="$JAVA_OPTS -Duser.dir=$APP_HOME"

  CLASS="com.ahaysoft.server.ServerApp"

  ARGS="--app.home=$APP_HOME --app.config.dir=$APP_HOME/config --app.tmp.dir=$APP_HOME/tmp"
  ARGS="$ARGS --build.version=$DATETIME"

  DAEMON_OPT=$(has_opt "-daemon" $@ )

  if [ "$DAEMON_OPT" = "true" ] ; then
    JAVA_OPTS="$JAVA_OPTS -Dspring.profiles.active=$PROFILE,deamon"
    ARGS="$ARGS --spring.config.location=$CONFIG_FILES"
    nohup "$JAVACMD" -cp "$CLASSPATH" $JAVA_OPTS $CLASS $ARGS $@ > $LOG_FILE 2>&1 < /dev/null &
    printf '%d' $! > $PID_FILE
  else
    JAVA_OPTS="$JAVA_OPTS -Dspring.profiles.active=$PROFILE"
    ARGS="$ARGS --spring.config.location=$CONFIG_FILES"
    exec "$JAVACMD" -cp "$CLASSPATH" $JAVA_OPTS $CLASS $ARGS $@
  fi
}


function stop() {
  STOP_OPT=$(has_opt "-stop" $@ )
  PID=`cat $PID_FILE`
  kill -9 $PID
  echo "Stopped processs $PID"
}

COMMAND=$1;
shift

echo "JAVA_HOME: $JAVA_HOME"
echo "JAVA_OPTS: $JAVA_OPTS"
echo "APP_HOME:  $APP_HOME"
cd $APP_HOME

if [ "$COMMAND" = "start" ] ; then
  start $@
elif [ "$COMMAND" = "stop" ] ; then
  stop $@
else
  echo "Usage: "
  echo "  To run the server as daemon"
  echo "    ./server.sh -daemon "
  echo "  To stop the daemon server"
  echo "    ./server.sh -stop "
  echo "  To run the server as console"
  echo "    ./server.sh"
  echo "  Optional parameters for the console mode:"
  echo "    --app.db.load=[test,none] to load the sample test data or an empty database"
  echo "    --server.port=7080 to override the default web server port"
fi