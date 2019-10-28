#!/bin/bash

output=$(ant test-and-print-score)
ant_rc=$?
echo "$output"
rc=0
if [ $ant_rc -ne 0 ]; then
  rc=1
  bash scripts/post_score.sh "-1"
else
  score_str=$(grep -Po "\d+ out of \d+" <<< "$output")
  if [ -z "$score_str" ]; then
    rc=2
    bash scripts/post_score.sh "0"
  else
    bash scripts/post_score.sh "$score_str"
  fi
fi
exit $rc