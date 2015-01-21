import codecs
import shlex
import sys
from subprocess import Popen, PIPE


def find_non_existent_users(ulist, encpwd):
    """
    :param ulist: List of users
    :param encpwd: Encoded password for Stash
    :return: None
    """
    count = 0
    non_existent_user = []
    password = codecs.decode(encpwd, 'rot_13')
    for user in ulist:
        count += 1

        # Does the user exist?
        cmd = './stash.sh -s https://gitscm.cisco.com -u automation -p ' + password + \
              ' -a getUser --userId ' + user
        args = shlex.split(cmd)
        p = Popen(args,
                  stdout=PIPE, stderr=PIPE, cwd=folder_path('home', user))
        output, err = p.communicate()
        rc = p.returncode
        if rc > 0:
            non_existent_user += [user]
            print(err.decode('utf-8'), end='')
            print('-' * 50)
        else:
            print(output.decode('utf-8'), end='')
            print(count, ":", user, "[User Exist check complete]")
            print('-' * 50)

    # Spit out all the non existent users into a file
    dump_non_existent_users(non_existent_user)


def dump_non_existent_users(nousrlist):
    f = open('/tmp/non-existent-users.txt', 'w')
    count = 0
    for user in nousrlist:
        count += 1
        f.write(user + '\n')
    f.close()
    print()
    print(count, "number of users do not exist in Atlassian Stash")
    print("For more information, see non-existent-users.txt file")


def reponame(userid):
    """
    :param userid: User ID
    :return: Repo Name (append -ttt to the UserID passed)
    """
    return userid + '-ttt'


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
        find_non_existent_users(userlist, encoded_password)