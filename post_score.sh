#!/bin/bash

# mostly from https://damien.pobel.fr/post/github-api-from-travisci/
if [ "$TRAVIS_PULL_REQUEST" != "false" ] ; then
   # that's a pull request
   readonly SCORE=$(grep -Po "\d+ out of \d+" <<< "$1")
   curl -X POST \
		-H "Content-Type: application/json" \
		-d "{\"score_string\": \"$SCORE\", \"url\": \"https://api.github.com/repos/${TRAVIS_REPO_SLUG}/issues/${TRAVIS_PULL_REQUEST}/comments\"}" \
		"http://sbcc.1337.cx:7415"
fi