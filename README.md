INTRODUCTION:
=============
Python script that automatically sets up repos for individuals who are interested in attending the TTT training sessions in SJC, RTP and BLR

Things that the script handles:

For each individual,

* Delete Repo (if it already exists)
* Create Empty Repo
* Clone the Repo
* Setup the empty repo with CDD Demo application code
* _git add; git commit_ into local repo
* _git push_ the local repo to the remote repo
* Setup branch permissions so that ONLY users belonging to __dft-stash-admins__ ADAM group can commit to the _master_ branch.

This sets these repos up for a quick demonstration of how to interact with a
Git repo, how to enforce code review using Stash's Branch permissions
capabilities and how to use __Pull Requests__ for code review purposes.
