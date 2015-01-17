import codecs
import shlex
import sys
from subprocess import Popen, PIPE


def create_repo(ulist, encpwd):
    """
    :param ulist: List of users
    :param encpwd: Encoded password for Stash
    :return: None
    """
    count = 0
    password = codecs.decode(encpwd, 'rot_13')
    for user in ulist:
        count += 1

        # Delete the Repo
        cmd = './stash.sh -s https://gitscm.cisco.com -u automation -p ' + password + \
              ' -a deleteRepository --project TTT -r ' + reponame(user)
        args = shlex.split(cmd)
        p = Popen(args,
                  stdout=PIPE, stderr=PIPE, cwd=folder_path('home', user))
        output, err = p.communicate()
        rc = p.returncode
        print(output.decode('utf-8'), end='')
        if rc > 0:
            print(err)
        print(count, ":", user, "[Repo Deleted]")

        # Create the Repo
        cmd = './stash.sh -s https://gitscm.cisco.com -u automation -p ' + password + ' -a createRepository ' + \
              '--project TTT -r ' + reponame(user)
        args = shlex.split(cmd)
        p = Popen(args,
                  stdout=PIPE, stderr=PIPE, cwd=folder_path('home', user))
        output, err = p.communicate()
        rc = p.returncode
        print(output.decode('utf-8'), end='')
        if rc > 0:
            print(err)
        print(count, ":", user, "[Repo Created]")

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

        # Add CDD to the Repo
        cmd = 'cp -r /tmp/cdd/ .'
        args = shlex.split(cmd)
        p = Popen(args,
                  stdout=PIPE, stderr=PIPE, cwd=folder_path('repo', user))
        output, err = p.communicate()
        rc = p.returncode
        print(output.decode('utf-8'), end='')
        if rc > 0:
            print(err)
        print(count, ":", user, "[CDD Base Code Added]")

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
        cmd = 'git commit -m "Initial Commit"'
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

        # Change Branch Permissions
        cmd = './stash.sh -s https://gitscm.cisco.com -u automation -p ' + password + ' -a grantBranchPermissions' + \
              ' --project TTT -r ' + reponame(user) + ' --branch master --type PATTERN --group stash-sdaas-admins'
        args = shlex.split(cmd)
        p = Popen(args,
                  stdout=PIPE, stderr=PIPE, cwd=folder_path('home', user))
        output, err = p.communicate()
        rc = p.returncode
        print(output.decode('utf-8'), end='')
        if rc > 0:
            print(err)
        print(count, ":", user, "[Change Branch Permissions complete]")


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


if __name__ == "__main__":
    if len(sys.argv) < 2:
        print('Usage: {} password'.format(sys.argv[0]))
        sys.exit(1)
    else:
        # You did not think I was going to give you the password that easy ;-)
        encoded_password = sys.argv[1]

        # Read the list of trainees from the external text file
        f = open('trainees.txt', 'r')
        userlist = []
        for line in f:
            userlist.append(line.rstrip())
        f.close()

        # Let's create the repos, shall we?
        create_repo(userlist, encoded_password)