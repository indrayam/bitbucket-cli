import shlex
from subprocess import Popen, PIPE


def update_pom_push_to_stash(ulist):
    """
    :param ulist: List of users
    :return: None
    """

    count = 0
    for user in ulist:
        count += 1

        # Delete Repo Locally
        cmd = 'rm -rf ' + reponame(user)
        args = shlex.split(cmd)
        p = Popen(args,
                  stdout=PIPE, stderr=PIPE, cwd=folder_path('repo_parent', user))
        output, err = p.communicate()
        rc = p.returncode
        print(output.decode('utf-8'), end='')
        if rc > 0:
            print(err)
        print(count, ":", user, "[Delete Repo Locally]")

        # Clone the Repo
        cmd = 'git clone ssh://git@gitscm.cisco.com/ttt/' + reponame(user) + '.git'
        args = shlex.split(cmd)
        p = Popen(args,
                  stdout=PIPE, stderr=PIPE, cwd=folder_path('repo_parent', user))
        output, err = p.communicate()
        rc = p.returncode
        print(output.decode('utf-8'), end='')
        if rc > 0:
            print(err)
        print(count, ":", user, "[Repo Cloned]")

        # Update the pom.xml file
        pomfilepath = '/Users/anand/workspace/_python_/python-projects/ttt-stash-cli/tmp/' + reponame(user) + '/pom.xml'
        f = open(pomfilepath, 'r')
        content = f.read()
        f.close()

        content = content.replace('it.gis.citeis.train-the-trainer.sdaastraining',
                                  'it.gis.citeis.train-the-trainer.sdaastraining.' + user, 1)

        f = open(pomfilepath, 'w')
        f.write(content)
        f.close()

        # Git Add
        cmd = 'git add .'
        args = shlex.split(cmd)
        p = Popen(args,
                  stdout=PIPE, stderr=PIPE, cwd=folder_path('repo', user))
        output, err = p.communicate()
        rc = p.returncode
        print(output.decode('utf-8'), end='')
        if rc > 0:
            print(err)
        print(count, ":", user, "[Git Add complete]")

        # Git Commit
        cmd = 'git commit -m "Updating the pom.xml file to make Group ID unique"'
        args = shlex.split(cmd)
        p = Popen(args,
                  stdout=PIPE, stderr=PIPE, cwd=folder_path('repo', user))
        output, err = p.communicate()
        rc = p.returncode
        print(output.decode('utf-8'), end='')
        if rc > 0:
            print(err)
        print(count, ":", user, "[Git Commit complete]")

        # Git Push
        cmd = 'git push origin master'
        args = shlex.split(cmd)
        p = Popen(args,
                  stdout=PIPE, stderr=PIPE, cwd=folder_path('repo', user))
        output, err = p.communicate()
        rc = p.returncode
        print(output.decode('utf-8'), end='')
        if rc > 0:
            print(err)
        print(count, ":", user, "[Git Push complete]")


    # Wrap up
    final_wrap_up(count)


def folder_path(leaf, user):
    """
    :param leaf: Custom string to qualify which folder path to return
    :param user: User Id
    :return: Folder Path to be used as current working directory
    """
    path = ''
    if leaf == 'repo':
        path += '/Users/anand/workspace/_python_/python-projects/ttt-stash-cli/tmp/' + reponame(user)
    elif leaf == 'repo_parent':
        path += '/Users/anand/workspace/_python_/python-projects/ttt-stash-cli/tmp/'
    elif leaf == 'home':
        path += '/Users/anand/workspace/_python_/python-projects/ttt-stash-cli/'

    return path


def reponame(userid):
    """
    :param userid: User ID
    :return: Repo Name (append -ttt to the UserID passed)
    """
    return userid + '-ttt'


def final_wrap_up(c):
    print("Number of total users in the input file:", c)


if __name__ == "__main__":
    # Read the list of trainees from the external text file
    f = open('trainees.txt', 'r')
    userlist = []
    for line in f:
        userlist.append(line.rstrip())
    f.close()

    # Let's create the repos, shall we?
    update_pom_push_to_stash(userlist)