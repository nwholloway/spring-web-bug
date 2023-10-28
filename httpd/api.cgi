#! /bin/sh -e

echo "Content-Type: text/plain"
echo

if [ -n "$REMOTE_USER" ]
then
    echo "Remote user: $REMOTE_USER"
fi

if [ -n "$CONTENT_TYPE" ]
then
  echo "Content type: $CONTENT_TYPE"
fi

if [ POST = "$REQUEST_METHOD" ]
then
  echo "Body:"
  dd bs=1 count="$CONTENT_LENGTH" status=none | od -c
fi
